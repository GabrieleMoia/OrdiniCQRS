package org.its.domain.order.events;

import org.its.bus.BusMessage;

import java.util.UUID;

public class EventRowOrder implements BusMessage {
    private UUID idOrdine;
    private int idProgressivo;
    private String descrizione;

    public EventRowOrder(UUID idOrdine, int idProgressivo, String descrizione) {
        this.idOrdine = idOrdine;
        this.idProgressivo = idProgressivo;
        this.descrizione = descrizione;
    }

    public EventRowOrder() {

    }

    public UUID getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(UUID idOrdine) {
        this.idOrdine = idOrdine;
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

}
