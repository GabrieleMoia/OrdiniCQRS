package org.its.projections;

import org.its.Entities.Good;
import org.its.bus.Bus;
import org.its.domain.Good.events.GoodAdded;
import org.its.domain.Good.events.GoodReserved;
import org.its.projections.dao.GoodsCountDao;

import javax.inject.Named;

@Named("GoodCountHandler")
public class GoodCountHandler {

    private final GoodsCountDao dao;
    private final Bus bus;

    public GoodCountHandler(
            GoodsCountDao dao,
            Bus bus) {
        this.dao = dao;
        this.bus = bus;
        this.bus.register(GoodAdded.class,
                (o) -> handle((GoodAdded) o));
        this.bus.register(GoodReserved.class,
                (o) -> handle((GoodReserved) o));
    }

    private void handle(GoodReserved o) {
        GoodCount goodCount = new GoodCount();
        goodCount.setDescrizione(o.getDescription());
        Good good = dao.getByName(o.getDescription());
        goodCount.setQuantity(good.getQuantità());
        dao.update(goodCount);
    }

    private void handle(GoodAdded o) {
        /*GoodCount goodCount = new GoodCount();
        goodCount.setDescrizione(o.getDescrizione());
        goodCount.setQuantity(o.getQuantità());
        dao.save(goodCount);*/
    }

}
