package org.its.events;

import org.its.bus.BusMessage;

import java.util.Date;
import java.util.UUID;

public class EventOrder implements BusMessage {
    private UUID id;
    private String name;
    private Date date;

    public EventOrder() {

    }

    public EventOrder(UUID id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
