package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDAO implements Dao<Item>{
    @Override
    public List<Item> readAll() {
        return null;
    }

    @Override
    public Item read(Long id) {
        return null;
    }

    @Override
    public Item create(Item item) {
        return null;
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public int delete(long id) {
        return 0;
    }

    @Override
    public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
