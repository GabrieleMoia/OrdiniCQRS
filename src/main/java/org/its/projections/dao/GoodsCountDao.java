package org.its.projections.dao;

import org.its.projections.GoodNotAvailable;

public interface GoodsCountDao {
    void save(GoodNotAvailable count);
    GoodNotAvailable getByName(String descrizione);
    void update(GoodNotAvailable count);
}
