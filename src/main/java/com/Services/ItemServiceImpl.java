package com.Services;

import java.util.List;

import com.Model.Item;
import com.dao.ItemDAO;
import com.dao.ItemDAOImpl;

public class ItemServiceImpl implements ItemService {

	private final ItemDAO itemDAO;

	public ItemServiceImpl() {
		this.itemDAO = new ItemDAOImpl();
	}

	@Override
	public  boolean addItem(Item item) {
		// TODO Auto-generated method stub
		return itemDAO.add(item);
	}

	@Override
	public Item getItemById(int id) {
		return itemDAO.getById(id);
	}

	@Override
	public List<Item> getAllItems() {
		return itemDAO.getAll();
	}

	@Override
	public boolean updateItem(Item item) {
		return itemDAO.update(item);
	}


}
