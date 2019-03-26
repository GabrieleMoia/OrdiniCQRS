package org.its.domain.Good.dao;

import org.its.Entities.Good;

public interface GoodDao {
    void save(Good good);
    Good getById(String nome);
    void update(Good good);
}
