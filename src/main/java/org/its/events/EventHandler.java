package org.its.events;

import org.its.orders.OrderDao;
import org.its.bus.Bus;
import org.its.orders.Order;

import java.util.UUID;

public class EventHandler {

    public EventHandler(
            OrderEventDao dao,
            Bus bus) {
        this.dao = dao;
        this.bus = bus;
        this.bus.register(EventOrder.class,
                (o) -> handle((EventOrder) o));
    }

    public int handle(EventOrder message) {
        return 0;
    }

    private final OrderEventDao dao;
    private final Bus bus;
}
