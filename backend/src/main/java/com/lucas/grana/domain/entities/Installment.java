package com.lucas.grana.domain.entities;

import java.time.LocalDate;

import com.lucas.grana.domain.valueObjects.Money;

public class Installment {
    private final int number;
    private final Money amount;
    private final LocalDate dueDate;
    private LocalDate paidDate;
    private boolean anticipated;

    public Installment(int number, Money amount, LocalDate dueDate, LocalDate paidDate, boolean anticipated) {
        this.number = number;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paidDate = paidDate;
        this.anticipated = anticipated;
    }

    public int getNumber() {
        return number;
    }

    public Money getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public boolean isAnticipated() {
        return anticipated;
    }

    public boolean isPaid() {
        return paidDate != null;
    }

    public void pay(LocalDate date) {
        if (isPaid()) {
            throw new IllegalStateException("Parcela já foi paga");
        }
        this.paidDate = date;
        this.anticipated = date.isBefore(dueDate);
    }

}
