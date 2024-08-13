package com.Services;

import java.util.List;
import com.Model.Record;


public interface RecordService {
	
	boolean addRecord(Record record);

	Record  getRecordById(int id);

	List<Record> getAllRecords();

	boolean updateRecord(Record record);

	boolean deleteRecord(int id);


}
