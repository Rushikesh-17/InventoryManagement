package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import com.Model.Item;
import com.util.HibernateUtil;

public class ItemDAOImpl implements ItemDAO {
	SessionFactory factory = getConn();

	private static SessionFactory getConn() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public boolean add(Item item) {
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Item> getAll() {
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			ArrayList<Item> item = (ArrayList<Item>) session.createQuery("From Item").list();
			session.getTransaction().commit();
			session.close();
			;
			return item;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Item item) {
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			session.update(item);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		Transaction transaction = null;
		try {
			Session session = factory.openSession();
			Item i = getById(id);
			transaction=session.beginTransaction();
			session.delete(i);
			transaction.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addOrUpdate(Item item) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try {
			Session session = factory.openSession();
			transaction = session.beginTransaction();

			// Check if an item with the same name already exists
			Item existingItem = getById(item.getId());

			if (existingItem != null) {
				update(existingItem);
			} else {
				add(item);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		}
		return false;
	}

//	public static 
}
