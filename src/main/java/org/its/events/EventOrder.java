package org.its.events;

import org.its.bus.BusMessage;

import java.util.UUID;

public class EventOrder implements BusMessage {
    private UUID orderId;
    private String nome_richiedente;

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getNome_richiedente() {
        return nome_richiedente;
    }

    public void setNome_richiedente(String nome_richiedente) {
        this.nome_richiedente = nome_richiedente;
    }
}
