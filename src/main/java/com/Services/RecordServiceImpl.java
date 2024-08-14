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


}
