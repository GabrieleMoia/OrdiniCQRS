package org.its.projections;

public class GoodCount {
    private String descrizione;
    private long quantity;

    public GoodCount(){

    }

    public GoodCount(String descrizione, long quantity) {
        this.descrizione = descrizione;
        this.quantity = quantity;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
