package org.its.events;

import org.its.bus.Bus;
import org.its.projections.OrderAmountById;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component
@Named("EventHandler")
public class EventHandler {

    public EventHandler(
            OrderEventDao dao,
            Bus bus) {
        this.dao = dao;
        this.bus = bus;
        this.bus.register(EventOrder.class,
                (o) -> handle((EventOrder) o));
        this.bus.register(EventRowOrder.class,
                (o) -> handle((EventRowOrder) o));
    }

    public void handle(EventOrder message) {
        OrderAmountById orderAmountById = new OrderAmountById();
        orderAmountById.setId(message.getId());
        orderAmountById.setAmount(0);
        dao.saveOrderAmount(orderAmountById);
    }

    public void handle(EventRowOrder message) {
        OrderAmountById orderAmountById = new OrderAmountById();
        orderAmountById.setId(message.getIdOrdine());
        orderAmountById.setAmount(message.getAmount());
        dao.updateOrderAmount(orderAmountById);
    }

    private final OrderEventDao dao;
    private final Bus bus;
}
