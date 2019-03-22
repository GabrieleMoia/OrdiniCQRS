package org.its.events;

import org.its.bus.BusMessage;

import java.util.UUID;

public class EventRowOrder implements BusMessage {
    private UUID idOrdine;
    private int id;
    private String descrizione;
    private double amount;

    public EventRowOrder() {

    }

    public EventRowOrder(UUID idOrdine, int id, String descrizione, double amount) {
        this.idOrdine = idOrdine;
        this.id = id;
        this.descrizione = descrizione;
        this.amount = amount;
    }

    public UUID getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(UUID idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
