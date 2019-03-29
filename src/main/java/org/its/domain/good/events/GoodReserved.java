package org.its.domain.good.events;

import org.its.bus.BusMessage;

import java.util.UUID;

public class GoodReserved implements BusMessage {
    private int rowId;
    private UUID orderId;
    private String description;
    private boolean checked;

    public GoodReserved(){

    }

    public GoodReserved(int rowId, UUID orderId, String description, boolean checked) {
        this.rowId = rowId;
        this.orderId = orderId;
        this.description = description;
        this.checked = checked;
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

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
