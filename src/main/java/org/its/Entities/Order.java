package org.its.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private String nome;
    private String data;
    private List<RowOrder> orderRows = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public List<RowOrder> getOrderRows() {
        return orderRows;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setOrderRows(List<RowOrder> orderRows) {
        this.orderRows = orderRows;
    }
}
