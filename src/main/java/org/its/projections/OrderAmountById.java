package org.its.projections;

import org.its.bus.Bus;
import org.its.bus.BusMessage;

import java.util.UUID;

public class OrderAmountById implements BusMessage {
    private UUID id;
    private double amount;

    public OrderAmountById() {

    }

    public OrderAmountById(UUID id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
