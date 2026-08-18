package com.alexduzi.expensetracking.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Set<Category> categories;
    private BigDecimal amountLimit;
    private PeriodType period;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Budget() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public BigDecimal getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(BigDecimal amountLimit) {
        this.amountLimit = amountLimit;
    }

    public PeriodType getPeriod() {
        return period;
    }

    public void setPeriod(PeriodType period) {
        this.period = period;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Budget budget)) return false;
        return Objects.equals(id, budget.id) && Objects.equals(user, budget.user) &&
                Objects.equals(categories, budget.categories) &&
                Objects.equals(amountLimit, budget.amountLimit) && period == budget.period &&
                Objects.equals(startDate, budget.startDate) && Objects.equals(endDate, budget.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, categories, amountLimit, period, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "user=" + user +
                ", id=" + id +
                ", amountLimit=" + amountLimit +
                ", period=" + period +
                '}';
    }
}
