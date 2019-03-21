package org.its.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.its.bus.Bus;
import org.its.command.CreateOrder;
import org.its.command.CreateOrderRow;
import org.its.events.EventRowOrder;
import org.springframework.stereotype.Component;
import org.its.events.EventOrder;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.UUID;


@Component
@Named("CommandHandler")
public class CommandHandler {


    ObjectMapper mapper = new ObjectMapper();
    private final OrderDao orderDao;
    private Bus bus;
    private int id = 0;

    @Inject
    public CommandHandler(@Named("orderDao") OrderDao orderDao, @Named("bus") Bus bus) {
        this.orderDao = orderDao;
        this.bus = bus;
        this.bus.register(CreateOrder.class,
                (o) -> handle((CreateOrder) o));
        this.bus.register(CreateOrderRow.class,
                (o) -> handle((CreateOrderRow) o));
    }

    private void handle(CreateOrderRow o) {
        Order order = orderDao.getById(o.getIdOrdine());

        RowOrder rowOrder = new RowOrder();

        rowOrder.setIdOrdine(o.getIdOrdine());
        rowOrder.setDescrizione(o.getDescrizione());
        rowOrder.setIdProgressivo(o.getProgressiveId());
        rowOrder.setValore(o.getValore());

        order.getOrderRows().add(rowOrder);
        orderDao.save(order);

        EventRowOrder eventRowOrder = new EventRowOrder();
        eventRowOrder.setIdOrdine(rowOrder.getIdOrdine());
        eventRowOrder.setValore(rowOrder.getValore());
        eventRowOrder.setIdProgressivo(rowOrder.getIdProgressivo());
        eventRowOrder.setDescrizione(rowOrder.getDescrizione());
        bus.send(eventRowOrder);
    }

    public UUID handle(CreateOrder o) throws Exception {
        Order order = new Order();
        order.setId(o.getId());
        order.setNome(o.getNome());
        order.setData(o.getData());
        EventOrder eventOrder = new EventOrder();
        eventOrder.setOrderId(order.getId());
        eventOrder.setNome_richiedente(order.getNome());
        bus.send(eventOrder);
        return orderDao.save(order);
    }

}
