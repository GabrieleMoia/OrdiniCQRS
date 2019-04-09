package org.its.projections;

import org.its.Entities.Good;
import org.its.bus.Bus;
import org.its.domain.good.dao.GoodDao;
import org.its.domain.order.events.EventOrder;
import org.its.domain.order.events.EventRowOrder;
import org.its.projections.dao.AmountByIdDAO;

import javax.inject.Named;

@Named("AmountByIdHandler")
public class AmountByIdHandler {


    public AmountByIdHandler(
            AmountByIdDAO dao,
            GoodDao goodDao,
            Bus bus) {
        this.goodDao = goodDao;
        this.dao = dao;
        this.bus = bus;
        this.bus.register(EventOrder.class,
                (o) -> handle((EventOrder) o));
        this.bus.register(EventRowOrder.class,
                (o) -> handle((EventRowOrder) o));
    }

    private void handle(EventRowOrder o) {
        AmountById amountById = new AmountById();
        amountById.setIdOrdine(o.getIdOrdine());
        Good goodFound = goodDao.getById(o.getDescrizione());
        amountById.setAmount(goodFound.getValore());
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
    private final GoodDao goodDao;
}
