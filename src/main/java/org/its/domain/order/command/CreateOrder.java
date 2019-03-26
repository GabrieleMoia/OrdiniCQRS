package org.its.domain.order.command;

import org.its.bus.BusMessage;

public class CreateOrder implements BusMessage {
    private String id;
    private String nome;
    private String data;

    public CreateOrder() {

    }

    public CreateOrder(String id, String nome, String data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
