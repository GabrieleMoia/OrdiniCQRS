package org.its.events;

import org.its.bus.Bus;
import org.its.projections.dao.AmountByIdDAO;

public class EventHandler {

    public EventHandler(
            AmountByIdDAO dao,
            Bus bus) {
        this.dao = dao;
        this.bus = bus;
        this.bus.register(EventOrder.class,
                (o) -> handle((EventOrder) o));
    }

    public int handle(EventOrder message) {
        return 0;
    }

    private final AmountByIdDAO dao;
    private final Bus bus;
}
