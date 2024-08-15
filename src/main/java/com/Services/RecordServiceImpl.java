package com.Services;

import java.io.File;
import java.util.List;

import com.Model.Record;
import com.dao.RecordDAO;
import com.dao.RecordDAOImpl;
import com.util.ExcelDataReader;

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
	public List<Record> getPaginatedRecords(int pageNo) {
		return recordDAO.getPaginatedRecords(pageNo);
	}
	
	

	@Override
	public boolean importRecords(File tempFile) {
		// TODO Auto-generated method stub
		
		List<Record> records =ExcelDataReader.importExcel(tempFile);
		
		return recordDAO.addAll(records);
	}

	@Override
	public long getRecordCount() {
		// TODO Auto-generated method stub
		return recordDAO.getRecordCount();
	}


	

}
