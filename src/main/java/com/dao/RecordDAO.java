package com.dao;

import java.util.List;

import com.Model.Record;

public interface RecordDAO {
	
	boolean add(Record record);
    Record getById(int id);
    List<Record> getAll();
    boolean update(Record record);
    boolean delete(int id);
    boolean addOrUpdate(Record record);

}
