package org.its.domain.order.events;

import org.its.bus.BusMessage;

import java.util.UUID;

public class EventRowOrder implements BusMessage {
    private UUID idOrdine;
    private int idProgressivo;
    private String descrizione;
    private double valore;

    public EventRowOrder(UUID idOrdine, int idProgressivo, String descrizione, double valore) {
        this.idOrdine = idOrdine;
        this.idProgressivo = idProgressivo;
        this.descrizione = descrizione;
        this.valore = valore;
    }

    public EventRowOrder() {

    }

    public UUID getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(UUID idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getIdProgressivo() {
        return idProgressivo;
    }

    public void setIdProgressivo(int idProgressivo) {
        this.idProgressivo = idProgressivo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getValore() {
        return valore;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }
}
