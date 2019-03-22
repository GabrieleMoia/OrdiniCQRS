package org.its.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.its.Entities.Order;
import org.its.Entities.RowOrder;
import org.its.bus.Bus;
import org.its.command.CreateOrder;
import org.its.command.CreateOrderRow;
import org.its.events.EventOrder;
import org.its.events.EventRowOrder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.ArrayList;
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
        Order order = orderDao.getById(UUID.fromString(o.getIdOrdine()));

        if(order.getOrderRows()==null){
            order.setOrderRows(new ArrayList<RowOrder>());
        }
        int id = order.getOrderRows().size()+1;

        RowOrder newRow = new RowOrder(id, o.getDescrizione(), o.getValore());
        order.getOrderRows().add(newRow);
        orderDao.update(order);

        EventRowOrder eventRowOrder = new EventRowOrder(UUID.fromString(o.getIdOrdine()),id,o.getDescrizione(),o.getValore());
        bus.send(eventRowOrder);
        /*OrderAmountById orderAmountById = new OrderAmountById();
        orderAmountById.setId(order.getId());
        orderAmountById.setAmount(o.getValore());
        bus.send(orderAmountById);

        OrderByName orderByName = new OrderByName();
        orderByName.setName(order.getNome());
        orderByName.setCount(1);
        bus.send(orderByName);*/

    }

    public void handle(CreateOrder o) throws Exception {
        Order order = new Order();
        order.setId(UUID.fromString(o.getId()));
        order.setNome(o.getNome());
        order.setData(o.getData());

        orderDao.save(order);

        EventOrder eventOrder = new EventOrder();
        eventOrder.setId(order.getId());
        eventOrder.setNome(order.getNome());
        eventOrder.setData(order.getData());
        bus.send(eventOrder);
        /*OrderAmountById orderAmountById = new OrderAmountById();
        orderAmountById.setId(order.getId());
        orderAmountById.setAmount(0.0);
        bus.send(orderAmountById);

        OrderByName orderByName = new OrderByName();
        orderByName.setName(order.getNome());
        orderByName.setCount(0);
        bus.send(orderByName);*/
    }

}
