package org.its.projections.dao;

import org.its.Entities.Order;
import org.its.projections.CountByName;

import java.util.UUID;

public interface CountByNameDAO {
    void save(CountByName orderNumberByName);
    Order getOrderById(UUID uuid);
    void update(CountByName orderNumberByName);

}
