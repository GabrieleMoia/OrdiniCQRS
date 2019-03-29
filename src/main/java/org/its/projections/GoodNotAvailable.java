package org.its.projections;

import org.its.bus.BusMessage;

public class GoodNotAvailable implements BusMessage {
    private String descrizione;
    private long quantity;

    public GoodNotAvailable(){

    }

    public GoodNotAvailable(String descrizione, long quantity) {
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
