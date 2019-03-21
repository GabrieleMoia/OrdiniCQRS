package org.its.events;

import org.its.orders.Order;

import java.util.List;
import java.util.UUID;

public interface OrderEventDao {
    UUID save(EventOrder eventOrder);
    List<Order> getById(UUID id);
}
