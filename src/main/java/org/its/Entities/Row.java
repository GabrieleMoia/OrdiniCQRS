package org.its.Entities;

import java.util.UUID;

public class Row {
    private UUID idOrdine;
    private int progressiveID;
    private String descrizione;
    private double valore;

    public Row(UUID idOrdine, int progressiveID, String descrizione, double valore) {
        this.idOrdine = idOrdine;
        this.progressiveID = progressiveID;
        this.descrizione = descrizione;
        this.valore = valore;
    }

    public Row() {

    }

    public UUID getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(UUID idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getProgressiveID() {
        return progressiveID;
    }

    public void setProgressiveID(int progressiveID) {
        this.progressiveID = progressiveID;
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
