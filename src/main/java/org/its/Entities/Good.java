package org.its.Entities;

public class Good {
    private String descrizione;
    private long quantità;

    public Good() {

    }

    public Good(String descrizione, long quantità) {
        this.descrizione = descrizione;
        this.quantità = quantità;
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
}
