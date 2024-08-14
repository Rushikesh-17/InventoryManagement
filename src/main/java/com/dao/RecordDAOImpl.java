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
		try {
			
			Item item = itemDAO.getByName(record.getItem().getName());
			if(item==null) {
				return false;
			}
			else {
				if(record.getAction().equals("added")) {
					item.setQuantity(item.getQuantity()+record.getUsedQuantity());
				}
				else if(record.getAction().equals("used")) {
					item.setQuantity(item.getQuantity()-record.getUsedQuantity());
				}
				record.setItem(item);
			}
			Session session = factory.openSession();
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
	}

	@Override
	public Record getById(int id) {
		Transaction t = null;
		try {
			Session session = factory.openSession();
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
	}

	@Override
	public List<Record> getAll() {
		Transaction t = null;
		try {
			Session session = factory.openSession();
			t = session.beginTransaction();
			ArrayList<Record> record = (ArrayList<Record>) session.createQuery("From Record").list();
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
	}

	@Override
	public boolean update(Record record) {
		Transaction t = null;
		try {
			Item item = itemDAO.getByName(record.getItem().getName());
			if(item==null) {
				return false;
			}
			else {
				record.setItem(item);
			}
			Session session = factory.openSession();
			t = session.beginTransaction();
			session.update(record);
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
	}

	@Override
	public boolean delete(int id) {
		Transaction t = null;
		try {
			
			Session session = factory.openSession();
			Record record = getById(id);
			Item item = itemDAO.getByName(record.getItem().getName());
			if(item==null) {
				return false;
			}
			else {
				record.setItem(item);
			}
			t = session.beginTransaction();
			session.delete(record);
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
	}

	@Override
	public boolean addOrUpdate(Record record) {
		try {
			// Check if an item with the same name already exists
			Record existingRecord = getById(record.getRecordId());

			if (existingRecord != null) {
				return update(record);
			} else {
				return add(record);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
