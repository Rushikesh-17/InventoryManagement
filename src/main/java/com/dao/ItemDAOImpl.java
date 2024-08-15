package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import com.Model.Item;
import com.util.HibernateUtil;

public class ItemDAOImpl implements ItemDAO {
	SessionFactory factory = getConn();

	private static SessionFactory getConn() {
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public boolean add(Item item) {
		Transaction transaction = null;
		try {
			Session session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(item);
			transaction.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
			return false;
		}
	}

	@Override
	public Item getById(int i) {
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Item item = session.get(Item.class, i);
			session.getTransaction().commit();
			session.close();
			return item;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Item getByName(String name) {
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			String hql = "from Item where name = :itemName";
			Query<Item> query = session.createQuery(hql, Item.class);
			query.setParameter("itemName", name);
			Item item = query.getSingleResult();
			session.getTransaction().commit();
			session.close();
			return item;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public List<Item> getAll() {
		try {
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			ArrayList<Item> item = (ArrayList<Item>) session.createQuery("From Item").list();
			transaction.commit();
			session.close();
			return item;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Item item) {
		Transaction transaction = null;
		int quantity = getById(item.getId()).getQuantity();
		item.setQuantity(quantity);
		try {
			Session session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(item);
			transaction.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		}
	}



	@Override
	public boolean addOrUpdate(Item item) {
		try {
			// Check if an item with the same name already exists
			Item existingItem = getById(item.getId());
			if (existingItem != null) {
				return update(item);
			} else {
				return add(item);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
