package org.its.orders;

import org.its.orders.Order;

import java.util.UUID;

public interface OrderDao {
    UUID save(Order order);
    Order getById(UUID id);
}
