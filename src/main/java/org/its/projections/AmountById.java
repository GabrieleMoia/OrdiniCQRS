package org.its.projections;

import java.util.UUID;

public class AmountById {
    private UUID id;
    private double amount;

    public AmountById() {

    }

    public AmountById(UUID idOrdine, double amount) {
        this.id = idOrdine;
        this.amount = amount;
    }

    public UUID getIdOrdine() {
        return id;
    }

    public void setIdOrdine(UUID idOrdine) {
        this.id = idOrdine;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
