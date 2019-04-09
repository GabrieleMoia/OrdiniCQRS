package org.its.domain.good.command;

import org.its.bus.BusMessage;

public class AddGood implements BusMessage {
    private String description;
    private long quantity;
    private double valore;

    public AddGood() {

    }

    public AddGood(String description, long quantity, double valore) {
        this.description = description;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getValore() {
        return valore;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }
}
