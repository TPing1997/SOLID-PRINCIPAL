package com.java.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class InsertService extends CRUDUtils implements CRUDInterface {

	public static void sample() throws IOException {
		// Result:
		// insert into db_data.dbo.user values ('val1','val2')
		// insert into db_data.dbo.user(column1, column2) values ('val1','val2')
		CRUDModel crudModel = sampleSetValue();
		System.out.println(preparingStatement(crudModel));
	}

	private static String preparingStatement(CRUDModel crudModel) {
		StringBuilder sb = new StringBuilder();
		for (CSVRecord insertValueCSVRecord : crudModel.getValueRecordList()) {
			crudModel.setValueRecord(insertValueCSVRecord);
			sb.append(concatStringWithBreakLine(executeStatement(crudModel)));

		}
		return sb.toString();
	}

	public static String executeStatement(CRUDModel crudModel) {
		String insertColumnString = new String();
		if (!ObjectUtils.isEmpty(crudModel.getColumnList())) {
			insertColumnString = listToString(crudModel.getColumnList());
		}

		String insertValueString = CSVRecordToString(crudModel.getValueRecord());

		crudModel.setColumnString(insertColumnString);
		crudModel.setValueString(insertValueString);
		return buildExecuteString(crudModel);
	}

	private static String buildExecuteString(CRUDModel crudModel) {
		StringBuilder insertStringBuilder = new StringBuilder();
		insertStringBuilder.append("INSERT INTO ");
		if (StringUtils.isNotBlank(crudModel.getTableName())) {
			insertStringBuilder.append(crudModel.getTableName());
		}
		insertStringBuilder.append(" ");
		insertStringBuilder.append(crudModel.getColumnString());
		insertStringBuilder.append(" VALUES (");
		insertStringBuilder.append(crudModel.getValueString());
		insertStringBuilder.append(");");
		return insertStringBuilder.toString();
	}

	private static CRUDModel sampleSetValue() throws IOException {

		CRUDModel insertModel = new InsertModel();
		List<String> insertColumnList = new ArrayList();
		insertColumnList.add("columnheader1");
		insertColumnList.add("columnheader2");
		insertColumnList.add("columnheader2");
		insertModel.setColumnList(insertColumnList);
		insertModel.setValueRecordList(insertValueCSVParser.getRecords());
		insertModel.setTableName("db_data.dbo.user");
		return insertModel;
	};
}
