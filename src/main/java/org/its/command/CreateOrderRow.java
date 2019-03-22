package org.its.command;

import org.its.bus.Bus;
import org.its.bus.BusMessage;

import java.util.UUID;

public class CreateOrderRow implements BusMessage{
    private String idOrdine;
    private String descrizione;
    private double valore;

    public CreateOrderRow() {

    }

    public CreateOrderRow(String idOrdine, String descrizione, double valore) {
        this.idOrdine = idOrdine;
        this.descrizione = descrizione;
        this.valore = valore;
    }

    public String getIdOrdine() {
        return idOrdine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getValore() {
        return valore;
    }
}
