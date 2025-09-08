package com.lucas.grana.domain.entities.transaction;

import java.util.List;

import com.lucas.grana.domain.valueObjects.Money;

public class Installments {
    private final Money total;
    private final List<Installment> installments;

    public Installments(Money total, List<Installment> installments) {
        if (installments == null || installments.isEmpty()) {
            throw new IllegalArgumentException("Installments não pode ser nulo ou vazio");
        }
        this.total = total;
        this.installments = installments;
    }

    public Money getTotal() {
        return total;
    }

    public List<Installment> getInstallments() {
        return installments;
    }
}
