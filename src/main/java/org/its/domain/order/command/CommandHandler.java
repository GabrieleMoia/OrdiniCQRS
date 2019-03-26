package org.its.domain.order.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.its.Entities.Order;
import org.its.Entities.RowOrder;
import org.its.bus.Bus;
import org.its.domain.Good.events.GoodReserved;
import org.its.domain.order.dao.OrderDao;
import org.its.domain.order.events.EventOrder;
import org.its.domain.order.events.EventRowOrder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.UUID;


@Component
@Named("OrderCommandHandler")
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
        this.bus.register(GoodReserved.class,
                (o) -> handle((GoodReserved) o));
    }

    private void handle(GoodReserved o) {
        Order order = orderDao.getById(o.getOrderId());
        if (o.isChecked()) {
            RowOrder rowOrder = order.getOrderRows().get(o.getRowId());
            rowOrder.setReserved(true);
            EventRowOrder orderRow = new EventRowOrder();
            orderRow.setIdOrdine(o.getOrderId());
            orderRow.setDescrizione(o.getDescription());
            orderRow.setValore(rowOrder.getValore());
            orderRow.setIdProgressivo(o.getRowId());
            orderDao.update(order);
            bus.send(orderRow);
        }
    }

    private void handle(CreateOrderRow o) {
        Order order = orderDao.getById(o.getIdOrdine());
        int id;
        if (order.getOrderRows().size() == 0) {
            order.setOrderRows(new ArrayList<RowOrder>());
            id = 0;
        } else {
            id = order.getOrderRows().size();
        }

        RowOrder newRow = new RowOrder(id, o.getDescrizione(), o.getValore());
        order.getOrderRows().add(newRow);
        orderDao.update(order);
        EventRowOrder eventRowOrder = new EventRowOrder(o.getIdOrdine(), id, o.getDescrizione(), o.getValore());
        bus.send(eventRowOrder);
    }

    public void handle(CreateOrder o) throws Exception {
        Order order = new Order();
        order.setId(UUID.fromString(o.getId()));
        order.setNome(o.getNome());
        order.setData(o.getData());

        orderDao.save(order);

        EventOrder eventOrder = new EventOrder();
        eventOrder.setId(order.getId());
        eventOrder.setName(order.getNome());
        bus.send(eventOrder);
    }

}
