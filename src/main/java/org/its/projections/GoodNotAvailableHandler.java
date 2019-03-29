package org.its.projections;

import org.its.bus.Bus;
import org.its.projections.dao.GoodsCountDao;

import javax.inject.Named;

@Named("GoodNotAvailableHandler")
public class GoodNotAvailableHandler {

    private final GoodsCountDao dao;
    private final Bus bus;

    public GoodNotAvailableHandler(
            GoodsCountDao dao,
            Bus bus) {
        this.dao = dao;
        this.bus = bus;
        this.bus.register(GoodNotAvailable.class,
                (o) -> handle((GoodNotAvailable) o));
    }

    private void handle(GoodNotAvailable o) {
        if (dao.getByName(o.getDescrizione()) == null) {
            o.setQuantity(1);
            dao.save(o);
        } else {
            GoodNotAvailable GoodNotAvailable = dao.getByName(o.getDescrizione());
            GoodNotAvailable.setQuantity(GoodNotAvailable.getQuantity() + 1);
            dao.update(GoodNotAvailable);
        }
    }
}
