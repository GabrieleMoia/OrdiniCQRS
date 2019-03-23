package org.its.projections;

import org.its.bus.Bus;
import org.its.events.EventOrder;
import org.its.events.EventRowOrder;
import org.its.projections.dao.AmountByIdDAO;

import javax.inject.Named;
import java.util.UUID;

@Named("AmountByIdHandler")
public class AmountByIdHandler {


    public AmountByIdHandler(
            AmountByIdDAO dao,
            Bus bus) {
        this.dao = dao;
        this.bus = bus;
        this.bus.register(EventOrder.class,
                (o) -> handle((EventOrder) o));
        this.bus.register(EventRowOrder.class,
                (o) -> handle((EventRowOrder) o));
    }

    private void handle(EventRowOrder o) {
        AmountById amountById = new AmountById();
        amountById.setIdOrdine(UUID.fromString(o.getIdOrdine()));
        amountById.setAmount(o.getValore());
        dao.update(amountById);
    }

    public void handle(EventOrder message) {
        AmountById amountById = new AmountById();
        amountById.setIdOrdine(message.getId());
        amountById.setAmount(0);
        dao.save(amountById);
    }

    private final AmountByIdDAO dao;
    private final Bus bus;
}
