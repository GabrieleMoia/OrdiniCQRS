package org.its.domain.good.dao;

import org.its.Entities.Good;

public interface GoodDao {
    void save(Good good);
    Good getById(String nome);
    void update(Good good);
}
