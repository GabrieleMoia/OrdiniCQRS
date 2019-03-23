package org.its.projections.dao;

import org.its.Entities.Order;
import org.its.projections.AmountById;

import java.util.List;
import java.util.UUID;

public interface AmountByIdDAO {

    void save(AmountById amount);
    void update(AmountById amount);
    List<Order> getById(UUID id);
}
