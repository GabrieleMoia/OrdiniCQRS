package org.its.domain.good.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.its.Entities.Good;
import org.its.EventController;
import org.its.bus.Bus;
import org.its.domain.good.dao.GoodDao;
import org.its.domain.good.events.GoodReserved;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("GoodCommandHandler")
@Component
public class CommandHandler {
    ObjectMapper mapper = new ObjectMapper();
    private final GoodDao goodDao;
    private Bus bus;
    private int id = 0;

    @Inject
    public CommandHandler(@Named("goodDao") GoodDao goodDao, @Named("bus") Bus bus) {
        this.goodDao = goodDao;
        this.bus = bus;
        this.bus.register(AddGood.class,
                (o) -> handle((AddGood) o));
        this.bus.register(ReserveGood.class,
                (o) -> handle((ReserveGood) o));
    }

    private void handle(ReserveGood o) throws Exception {
        EventController controller = new EventController();
        GoodReserved reserved;
        List<Good> goods = controller.getGoods();
        if (!goods.isEmpty()) {
            Good good = goodDao.getById(o.getDescription().toLowerCase());
            boolean checked = false;
            if (good != null) {
                if (good.getQuantità() > 0) {
                    good.setQuantità(good.getQuantità() - 1);
                    goodDao.update(good);
                    checked = true;
                }
            }
            //quando blocco una merce, richiamo l'evento sul blocco delle merci. In ascolto c'è l'istanza sul event handler
            reserved = new GoodReserved(o.getRowId(), o.getOrderId(), o.getDescription().toLowerCase(), checked);
            bus.send(reserved);
        } else {
            throw new Exception("Merci non disponibile");
        }
    }

    private void handle(AddGood o) {
        Good oldGood = goodDao.getById(o.getDescription().toLowerCase());
        Good good = new Good();

        if (null == oldGood) {
            good.setDescrizione(o.getDescription().toLowerCase());
            good.setQuantità(o.getQuantity());
            good.setValore(o.getValore());
            goodDao.save(good);
        } else {
            good.setDescrizione(o.getDescription().toLowerCase());
            good.setQuantità(o.getQuantity() + oldGood.getQuantità());
            goodDao.update(good);
        }
    }
}
