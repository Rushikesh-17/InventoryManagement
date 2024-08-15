package com.dao;

import java.util.List;

import com.Model.Record;

public interface RecordDAO {
	
	boolean add(Record record);
    Record getById(int id);
    List<Record> getAll();
	boolean addAll(List<Record> records);
	List<Record> getPaginatedRecords(int pageNumber);
	long getRecordCount();


}
