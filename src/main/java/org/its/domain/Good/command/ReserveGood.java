package org.its.domain.Good.command;

import org.its.bus.BusMessage;

import java.util.UUID;

public class ReserveGood implements BusMessage {
    private int rowId;
    private UUID orderId;
    private String description;

    public ReserveGood() {

    }

    public ReserveGood(int rowId, UUID id, String description) {
        this.rowId = rowId;
        this.orderId = id;
        this.description = description;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID id) {
        this.orderId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
