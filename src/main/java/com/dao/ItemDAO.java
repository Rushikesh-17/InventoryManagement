package com.dao;

import java.util.List;

import com.Model.Item;

public interface ItemDAO {
	boolean add(Item item);
    Item getById(int id);
    List<Item> getAll();
    boolean update(Item item);
    boolean delete(int id);
    boolean addOrUpdate(Item item);

    
}
