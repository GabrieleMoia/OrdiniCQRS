package org.its.domain.good.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.its.Entities.Order;
import org.its.Entities.RowOrder;
import org.its.bus.Bus;
import org.its.domain.order.dao.OrderDao;
import org.its.domain.order.events.EventRowOrder;
import org.its.projections.GoodNotAvailable;

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
    private void handle(GoodReserved o) throws Exception {
        Order order = orderDao.getById(o.getOrderId());
        if (o.isChecked()) {
            RowOrder rowOrder = order.getOrderRows().get(o.getRowId());
            rowOrder.setReserved(true);
            EventRowOrder orderRow = new EventRowOrder();
            orderRow.setIdOrdine(o.getOrderId());
            orderRow.setDescrizione(o.getDescription());
            orderRow.setIdProgressivo(o.getRowId());
            orderDao.update(order);
            EventRowOrder eventRowOrder = new EventRowOrder(o.getOrderId(), o.getRowId(), o.getDescription());
            bus.send(eventRowOrder);
        } else {
            orderDao.deleteRow(order, o.getRowId());
            GoodNotAvailable GoodNotAvailable = new GoodNotAvailable(o.getDescription().toLowerCase(), 1);
            bus.send(GoodNotAvailable);
            throw new Exception("Merce non disponibile");
        }
    }
}

