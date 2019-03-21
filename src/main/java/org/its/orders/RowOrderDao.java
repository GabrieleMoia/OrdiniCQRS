package org.its.orders;

import java.util.List;
import java.util.UUID;

public interface RowOrderDao {
    void save(UUID id);
    List<RowOrder> getOrderById();
}
