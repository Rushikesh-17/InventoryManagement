package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.Model.Item;
import com.Model.Record;
import com.util.HibernateUtil;

public class RecordDAOImpl implements RecordDAO {
	private final SessionFactory factory = getConn();

	private final ItemDAO itemDAO = new ItemDAOImpl();

	private static SessionFactory getConn() {
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public boolean add(Record record) {
		// TODO Auto-generated method stub
		Transaction t = null;
		Session session = factory.openSession();
		try {

			Item item = itemDAO.getByName(record.getItem().getName());
			if (item == null) {
				return false;
			} else {
				if (record.getAction().equals("added")) {
					item.setQuantity(item.getQuantity() + record.getUsedQuantity());
				} else if (record.getAction().equals("used")) {
					item.setQuantity(item.getQuantity() - record.getUsedQuantity());
				}
				record.setItem(item);
			}
			t = session.beginTransaction();
			session.save(record);
			t.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (t != null) {
				t.rollback();
			}
			return false;
		}
		finally {
			session.close();
		}
	}

	@Override
	public Record getById(int id) {
		Transaction t = null;
		Session session = factory.openSession();
		try {
			t = session.beginTransaction();
			Record record = session.get(Record.class, id);
			t.commit();
			session.close();
			return record;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (t != null) {
				t.rollback();
			}
			return null;
		}
		finally {
			session.close();
		}
	}

	@Override
	public List<Record> getAll() {
		Transaction t = null;
		Session session = factory.openSession();

		try {
			t = session.beginTransaction();
			ArrayList<Record> record = (ArrayList<Record>) session.createQuery("From Record").list();
			t.commit();
			return record;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (t != null) {
				t.rollback();
			}
			return null;
		}
		finally {
			session.close();
		}
	}

	@Override
	public boolean addAll(List<Record> records) {
		Transaction t = null;
		try {

			for (Record record : records) {
				Session session = factory.openSession();
				t = session.beginTransaction();


				Item item = itemDAO.getByName(record.getItemname());
				if (item == null) {
					return false;
					//raise itemNotFoundException
				} else {
					record.setItem(item);
					if (record.getAction().equals("added")) {
						item.setQuantity(item.getQuantity() + record.getUsedQuantity());
					} else if (record.getAction().equals("used")) {
						item.setQuantity(item.getQuantity() - record.getUsedQuantity());
					}
					record.setItem(item);
				}

				session.save(record);
				t.commit();

			}
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (t != null) {
				t.rollback();
			}
			//raise hibernate exception
			return false;
		}
	
	}

}
