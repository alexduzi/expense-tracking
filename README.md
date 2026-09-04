┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│ Expense Service │     │ Budget Service  │     │ Notification    │
│                 │────▶│                 │────▶│ Service         │
│ - criar gasto   │     │ -verificar      │     │ - email/push    │
│ - categorizar   │     │  limite         │     │   quando limite │
│ - salvar        │     │ -atualizar saldo│     │   atingido      │
└─────────────────┘     └─────────────────┘     └─────────────────┘

expense-tracking/
├── expense-service/          # cria/deleta gastos + outbox
│   ├── domain/
│   ├── outbox/               # OutboxEvent, OutboxWorker
│   └── kafka/                # producers
├── budget-service/           # consome eventos, atualiza saldo
│   ├── domain/
│   └── kafka/                # consumers
├── notification-service/     # envia email/push quando limite atingido
├── docker-compose.yml        # PostgreSQL + Kafka
└── README.md                 # documenta os padrões usados

# Regras:

Outbox Pattern — Expense Service:

Quando criar um gasto, precisa notificar o Budget Service. 
O problema: se salvar no banco e o Kafka cair antes de publicar, o Budget Service nunca fica sabendo.

Exemplo:

```java
@Transactional
public Expense createExpense(ExpenseRequest request) {
// salva o gasto
Expense expense = expenseRepository.save(new Expense(request));

    // salva o evento na MESMA transação
    OutboxEvent event = new OutboxEvent(
        "expense.created",
        objectMapper.writeValueAsString(expense)
    );
    outboxRepository.save(event);
    
    return expense;
    // transação commita os dois juntos — gasto + evento
}

// Worker separado que roda a cada X segundos
@Scheduled(fixedDelay = 1000)
public void publishOutboxEvents() {
    List<OutboxEvent> events = outboxRepository.findUnpublished();
    for (OutboxEvent event : events) {
        kafkaTemplate.send(event.getTopic(), event.getPayload());
        event.markAsPublished();
        outboxRepository.save(event);
    }
}
```

Eventual Consistency — Budget Service:

O Budget Service consome o evento e atualiza o saldo. 
Haverá um momento onde o gasto foi criado mas o budget ainda não foi atualizado, isso é eventual consistency.

```java
@KafkaListener(topics = "expense.created")
public void onExpenseCreated(ExpenseCreatedEvent event) {
    Budget budget = budgetRepository
        .findByUserAndCategory(event.getUserId(), event.getCategory())
        .orElseThrow();
    
    budget.deductAmount(event.getAmount());
    budgetRepository.save(budget);
    
    if (budget.isLimitExceeded()) {
        // publica evento para Notification Service
        kafkaTemplate.send("budget.limit.exceeded", 
            new BudgetExceededEvent(budget));
    }
}
```

Saga Pattern — para deleção de gasto:

Quando o usuário deleta um gasto, você precisa:

- Deletar o gasto no Expense Service
- Reverter o valor no Budget Service
- Se qualquer etapa falhar, compensar

```java
// Choreography-based Saga

// Expense Service
@Transactional
public void deleteExpense(Long expenseId) {
    Expense expense = expenseRepository.findById(expenseId)
        .orElseThrow();
    
    expenseRepository.delete(expense);
    
    // publica evento para Budget Service reverter
    outboxRepository.save(new OutboxEvent(
        "expense.deleted",
        objectMapper.writeValueAsString(
            new ExpenseDeletedEvent(expense))
    ));
}

// Budget Service — ouve e compensa
@KafkaListener(topics = "expense.deleted")
public void onExpenseDeleted(ExpenseDeletedEvent event) {
    Budget budget = budgetRepository
        .findByUserAndCategory(event.getUserId(), event.getCategory())
        .orElseThrow();
    
    // transação compensatória — devolve o valor
    budget.addAmount(event.getAmount());
    budgetRepository.save(budget);
}
```