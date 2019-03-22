package org.its.events;

import org.its.bus.BusMessage;

import java.util.UUID;

public class EventOrder implements BusMessage {
    private UUID id;
    private String nome;
    private String data;

    public EventOrder(){

    }

    public EventOrder(UUID id, String nome, String data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
