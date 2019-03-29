package org.its;

import org.its.bus.Bus;
import org.its.domain.good.command.AddGood;
import org.its.domain.good.command.ReserveGood;
import org.its.domain.order.command.CreateOrder;
import org.its.domain.order.command.CreateOrderRow;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Named;
import java.util.UUID;

@Controller
@RequestMapping("/api")
public class CommandController {

    private final Bus bus;

    public CommandController(@Named("bus") Bus bus) {
        this.bus = bus;
    }

    @RequestMapping(
            path = "/ordine/insertOrdine",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public String createOrder(@RequestBody CreateOrder createOrder) throws Exception {
        createOrder.setId(UUID.randomUUID().toString());
        bus.send(createOrder);
        return createOrder.getId();
    }

    @RequestMapping(
            path = "/ordine/insertRow",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public String createOrderRow(@RequestBody CreateOrderRow createOrderRow) throws Exception {
        bus.send(createOrderRow);
        return createOrderRow.getIdOrdine().toString();
    }

    @RequestMapping(
            path = "merce/insertGoods",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public String insertGoods(@RequestBody AddGood good) throws Exception {
        bus.send(good);
        return good.getDescription();
    }

    @RequestMapping(
            path = "merce/reserveGoods",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public String reserveGoods(@RequestBody ReserveGood good) throws Exception {
        bus.send(good);
        return good.getDescription();
    }
}
