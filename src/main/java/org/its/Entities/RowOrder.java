package org.its.Entities;

import java.util.UUID;

public class RowOrder {
    private int idProgressivo;
    private String descrizione;
    private double valore;

    public RowOrder(){

    }

    public RowOrder(int idProgressivo, String descrizione, double valore) {
        this.idProgressivo = idProgressivo;
        this.descrizione = descrizione;
        this.valore = valore;
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
