package org.its.domain.Good.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.its.Entities.Good;
import org.its.bus.Bus;
import org.its.domain.Good.dao.GoodDao;
import org.its.domain.Good.events.GoodAdded;
import org.its.domain.Good.events.GoodReserved;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

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

    private void handle(ReserveGood o) {
        Good oldGood = goodDao.getById(o.getDescription());
        boolean checked = false;

        if(oldGood.getQuantità() > 0){
            oldGood.setQuantità(oldGood.getQuantità() -1);
            goodDao.update(oldGood);
            checked = true;
        }
        GoodReserved reserved = new GoodReserved(o.getRowId(), o.getOrderId(), o.getDescription(), checked);
        bus.send(reserved);
    }

    private void handle(AddGood o) {
        Good oldGood = goodDao.getById(o.getDescription());
        Good good = new Good();

        if (null == oldGood) {
            good.setDescrizione(o.getDescription());
            good.setQuantità(o.getQuantity());
            goodDao.save(good);
        } else {
            good.setDescrizione(o.getDescription());
            good.setQuantità(o.getQuantity() + oldGood.getQuantità());
            goodDao.update(good);
        }
        GoodAdded goodAdded = new GoodAdded(good.getDescrizione(), good.getQuantità());
        bus.send(goodAdded);
    }
}
