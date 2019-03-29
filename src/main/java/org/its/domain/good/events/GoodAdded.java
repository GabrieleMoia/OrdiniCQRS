package org.its.domain.good.events;

import org.its.bus.BusMessage;

public class GoodAdded implements BusMessage {
    private String descrizione;
    private long quantità;

    public GoodAdded(){

    }

    public GoodAdded(String descrizione, long quantità) {
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
}
