package com.util;

import java.io.File;
import java.util.List;
import com.Model.Record;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

public class ExcelDataReader {

	public static List<Record> importExcel(File excelFile) {
		PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
				.build();
		
		List<Record> dataList = Poiji.fromExcel(excelFile, Record.class, options);
		return dataList;
	}

}
