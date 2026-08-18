package com.alexduzi.expensetracking.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private LocalDate targetDate;

    public Goal() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Goal goal)) return false;
        return Objects.equals(id, goal.id) && Objects.equals(user, goal.user) && Objects.equals(title, goal.title) && Objects.equals(targetAmount, goal.targetAmount) && Objects.equals(currentAmount, goal.currentAmount) && Objects.equals(targetDate, goal.targetDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, targetAmount, currentAmount, targetDate);
    }

    @Override
    public String toString() {
        return "Goal{" +
                "title='" + title + '\'' +
                '}';
    }
}
