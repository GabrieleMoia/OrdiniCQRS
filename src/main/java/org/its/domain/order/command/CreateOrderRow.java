package org.its.domain.order.command;

import org.its.bus.BusMessage;

import java.util.UUID;

public class CreateOrderRow implements BusMessage{
    private UUID idOrdine;
    private String descrizione;
    private double valore;

    public CreateOrderRow() {

    }

    public CreateOrderRow(UUID idOrdine, String descrizione, double valore) {
        this.idOrdine = idOrdine;
        this.descrizione = descrizione;
        this.valore = valore;
    }

    public UUID getIdOrdine() {
        return idOrdine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getValore() {
        return valore;
    }

    public void setIdOrdine(UUID idOrdine) {
        this.idOrdine = idOrdine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }
}
