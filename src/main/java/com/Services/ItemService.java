package com.Services;

import java.util.List;

import com.Model.Item;

public interface ItemService {

	boolean addItem(Item item);

	Item getItemById(int id);

	List<Item> getAllItems();

	boolean updateItem(Item item);

}
