package org.its.projections;

import org.its.Entities.Order;
import org.its.bus.Bus;
import org.its.domain.order.events.EventOrder;
import org.its.domain.order.events.EventRowOrder;
import org.its.projections.dao.CountByNameDAO;

import javax.inject.Named;

@Named("CountByNameHandler")
public class CountByNameHandler {
    public CountByNameHandler(
            CountByNameDAO dao,
            Bus bus) {
        this.dao = dao;
        this.bus = bus;
        this.bus.register(EventOrder.class,
                (o) -> handle((EventOrder) o));
        this.bus.register(EventRowOrder.class,
                (o) -> handle((EventRowOrder) o));
    }

    private void handle(EventRowOrder o) {
        CountByName orderNumberByName = new CountByName();
        Order order = dao.getOrderById(o.getIdOrdine());
        orderNumberByName.setNome(order.getNome());
        orderNumberByName.setCount(1);
        dao.update(orderNumberByName);
    }

    public void handle(EventOrder message) {
        CountByName orderNumberByName = new CountByName();
        orderNumberByName.setNome(message.getName());
        orderNumberByName.setCount(0);
        dao.save(orderNumberByName);
    }

    private final CountByNameDAO dao;
    private final Bus bus;
}
