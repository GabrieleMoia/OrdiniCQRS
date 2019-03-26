package org.its.projections.dao;

import org.its.Entities.Good;
import org.its.projections.GoodCount;

public interface GoodsCountDao {
    void save(GoodCount count);
    Good getByName(String descrizione);
    void update(GoodCount count);
}
