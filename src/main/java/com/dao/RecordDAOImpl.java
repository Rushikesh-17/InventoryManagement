package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.Model.Item;
import com.Model.Record;
import com.util.HibernateUtil;

public class RecordDAOImpl implements RecordDAO {
	SessionFactory factory = getConn();

	private static SessionFactory getConn() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory();
	}


	@Override
	public boolean add(Record record) {
		// TODO Auto-generated method stub
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			session.save(record);
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
	public Record getById(int id) {
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Record record = session.get(Record.class, id);
			session.getTransaction().commit();
			session.close();
			return record;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Record> getAll() {
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			ArrayList<Record> record = (ArrayList<Record>) session.createQuery("From Record").list();
			session.getTransaction().commit();
			session.close();
			return record;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Record record) {
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			session.update(record);
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
		try {
			Session session = factory.openSession();
			Record r = getById(id);
			session.beginTransaction();
			session.delete(r);
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
	public boolean addOrUpdate(Record record) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
