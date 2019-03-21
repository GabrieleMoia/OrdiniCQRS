package org.its.command;

import org.its.bus.Bus;
import org.its.bus.BusMessage;

import java.util.UUID;

public class CreateOrderRow implements BusMessage{
    private UUID idOrdine;
    private int progressiveId;
    private String descrizione;
    private double valore;

    public CreateOrderRow() {

    }

    public CreateOrderRow(UUID idOrdine, int id, String descrizione, double valore) {
        this.idOrdine = idOrdine;
        this.descrizione = descrizione;
        this.valore = valore;
    }

    public UUID getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(UUID idOrdine) {
        this.idOrdine = idOrdine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getProgressiveId() {
        return progressiveId;
    }

    public void setProgressiveId(int progressiveId) {
        this.progressiveId = progressiveId;
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
