package org.its.Entities;

public class RowOrder {
    private int idProgressivo;
    private String descrizione;
    private boolean reserved;

    public RowOrder() {

    }

    public RowOrder(int idProgressivo, String descrizione) {
        this.idProgressivo = idProgressivo;
        this.descrizione = descrizione;
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

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
