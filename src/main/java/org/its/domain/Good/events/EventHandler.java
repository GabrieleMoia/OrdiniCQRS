package org.its.domain.Good.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.its.Entities.Order;
import org.its.Entities.RowOrder;
import org.its.bus.Bus;
import org.its.domain.order.dao.OrderDao;
import org.its.domain.order.events.EventRowOrder;

import javax.inject.Inject;
import javax.inject.Named;

@Named("EventHandler")
public class EventHandler {

    ObjectMapper mapper = new ObjectMapper();
    private final OrderDao orderDao;
    private Bus bus;
    private int id = 0;

    @Inject
    public EventHandler(@Named("orderDao") OrderDao orderDao, @Named("bus") Bus bus) {
        this.orderDao = orderDao;
        this.bus = bus;
        this.bus.register(GoodReserved.class,
                (o) -> handle((GoodReserved) o));
    }

    //Evento che deriva dalla chiamata del command delle merci. Setto la riga corretta su db a true. Come se l'ordine fosse stato sccetatto
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
            EventRowOrder eventRowOrder = new EventRowOrder(o.getOrderId(), o.getRowId(), o.getDescription(), rowOrder.getValore());
            bus.send(eventRowOrder);
        }else{
            orderDao.deleteRow(order, o.getRowId());
        }
    }
}
