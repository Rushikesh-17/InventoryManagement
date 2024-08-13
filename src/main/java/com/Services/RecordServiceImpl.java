package com.Services;

import java.util.List;

import com.Model.Record;
import com.dao.RecordDAO;
import com.dao.RecordDAOImpl;

public class RecordServiceImpl implements RecordService{
	
	private final RecordDAO recordDAO;
	
	public RecordServiceImpl() {
		this.recordDAO = new RecordDAOImpl();
	}

	@Override
	public boolean addRecord(Record record) {
		return recordDAO.add(record);
	}

	@Override
	public Record getRecordById(int id) {
		// TODO Auto-generated method stub
		return recordDAO.getById(id);
	}

	@Override
	public List<Record> getAllRecords() {
		// TODO Auto-generated method stub
		return recordDAO.getAll();
	}

	@Override
	public boolean updateRecord(Record record) {
		// TODO Auto-generated method stub
		return recordDAO.update(record);
	}

	@Override
	public boolean deleteRecord(int id) {
		// TODO Auto-generated method stub
		return recordDAO.delete(id);
	}

}
