package org.its.Entities;

public class Good {
    private String descrizione;
    private long quantità;
    private double valore;

    public Good() {

    }

    public Good(String descrizione, long quantità, double valore) {
        this.descrizione = descrizione;
        this.quantità = quantità;
        this.valore = valore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public long getQuantità() {
        return quantità;
    }

    public void setQuantità(long quantità) {
        this.quantità = quantità;
    }

    public double getValore() {
        return valore;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }
}
