package org.its.domain.good.events;

import org.its.bus.BusMessage;

public class GoodAdded implements BusMessage {
    private String descrizione;
    private long quantità;
    private double valore;

    public GoodAdded() {

    }

    public GoodAdded(String descrizione, long quantità, double valore) {
        this.descrizione = descrizione;
        this.quantità = quantità;
    }

    public long getQuantità() {
        return quantità;
    }

    public void setQuantità(long quantità) {
        this.quantità = quantità;
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
