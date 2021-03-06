package org.its;

import org.its.bus.Bus;
import org.its.command.CreateOrder;
import org.its.command.CreateOrderRow;
import org.its.command.CommandHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Named;
import java.util.UUID;

@Controller
@RequestMapping("/api/ordine")
public class CommandController {

    private final Bus bus;
    private final CommandHandler commandHandler;

    public CommandController(@Named("bus") Bus bus, @Named("CommandHandler") CommandHandler commandHandler) {
        this.bus = bus;
        this.commandHandler = commandHandler;
    }

    @RequestMapping(
            path = "/insertOrdine",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public String createOrder(@RequestBody CreateOrder createOrder) throws Exception {
        createOrder.setId(UUID.randomUUID().toString());
        bus.send(createOrder);
        return createOrder.getId();
    }

    @RequestMapping(
            path = "/insertRow",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public String createOrderRow(@RequestBody CreateOrderRow createOrderRow) throws Exception {
        bus.send(createOrderRow);
        return createOrderRow.getIdOrdine();
    }
}
