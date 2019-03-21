package org.its.command;

import org.its.bus.BusMessage;

import java.util.UUID;

public class CreateOrder implements BusMessage {
    private UUID id;
    private String nome;
    private String data;

    public CreateOrder() {

    }

    public CreateOrder(UUID id, String nome, String data) {
        this.nome = nome;
        this.data = data;
        this.id = id;
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
