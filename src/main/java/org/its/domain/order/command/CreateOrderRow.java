package org.its.domain.order.command;

import org.its.bus.BusMessage;

import java.util.UUID;

public class CreateOrderRow implements BusMessage {
    private UUID idOrdine;
    private String descrizione;

    public CreateOrderRow() {

    }

    public CreateOrderRow(UUID idOrdine, String descrizione) {
        this.idOrdine = idOrdine;
        this.descrizione = descrizione;
    }

    public UUID getIdOrdine() {
        return idOrdine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setIdOrdine(UUID idOrdine) {
        this.idOrdine = idOrdine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
