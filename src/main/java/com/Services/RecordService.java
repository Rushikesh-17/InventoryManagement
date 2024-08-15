package com.Services;

import java.io.File;
import java.util.List;
import com.Model.Record;


public interface RecordService {
	
	boolean addRecord(Record record);

	Record  getRecordById(int id);

	List<Record> getAllRecords();

	boolean importRecords(File tempFile);




}
