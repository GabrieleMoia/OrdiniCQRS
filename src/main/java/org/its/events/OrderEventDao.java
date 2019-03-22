package org.its.events;

import org.its.Entities.Order;
import org.its.projections.OrderAmountById;

import java.util.List;
import java.util.UUID;

public interface OrderEventDao {
    void saveOrderAmount(OrderAmountById orderAmountById);
    void updateOrderAmount(OrderAmountById orderAmountById);
    List<Order> getById(UUID id);
}
