package org.its.domain.Good.command;

import org.its.bus.BusMessage;

public class AddGood implements BusMessage {
    private String description;
    private long quantity;

    public AddGood() {

    }

    public AddGood(String description, long quantity) {
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
}
