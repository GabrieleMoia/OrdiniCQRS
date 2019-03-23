package org.its.command.dao;

import org.its.Entities.Order;

import java.util.UUID;

public interface OrderDao {
    void save(Order order);
    Order getById(UUID id);
    void update(Order order);
}
