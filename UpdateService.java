package com.java.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

public class UpdateService extends CRUDUtils implements CRUDInterface {

	public static void sample() throws IOException {
		// Result:
		// update db_data.dbo.user set value = 'something' where value = 'something'
		// update db_data.dbo.user set value = 'something'
		UpdateModel updateModel = sampleSetValue();
		System.out.println(preparingStatement(updateModel));
	}

	private static String preparingStatement(CRUDModel crudModel) {
		List<CSVRecord> updateValueRecordList = crudModel.getValueRecordList();
		List<CSVRecord> WhereValueRecordList = crudModel.getWhereValueRecordList();
		StringBuilder sb = new StringBuilder();
		for (Integer i = 0; i < updateValueRecordList.size(); i++) {
			crudModel.setValueRecord(updateValueRecordList.get(i));
			crudModel.setWhereValueRecord(WhereValueRecordList.get(i));
			sb.append(concatStringWithBreakLine(executeStatement(crudModel)));
		}
		return sb.toString();
	}

	public static String executeStatement(CRUDModel crudModel) {
		String updateString = convertColumnListToString(crudModel.getColumnList(), crudModel.getValueRecord());
		String WhereString = convertWhereListToString(crudModel.getWhereList(), crudModel.getWhereValueRecord());
		crudModel.setColumnString(updateString);
		crudModel.setWhereString(WhereString);
		return buildExecuteString(crudModel);
	}

	private static String buildExecuteString(CRUDModel crudModel) {
		StringBuilder updateBuilderString = new StringBuilder();
		updateBuilderString.append("UPDATE ");
		updateBuilderString.append(crudModel.getTableName());
		updateBuilderString.append(" SET ");
		updateBuilderString.append(crudModel.getColumnString());
		if (StringUtils.isNotEmpty(crudModel.getWhereString())) {
			updateBuilderString.append(" WHERE ");
			updateBuilderString.append(crudModel.getWhereString());
		}
		return updateBuilderString.toString();
	}

	public static UpdateModel sampleSetValue() throws IOException {
		List<String> updateColumnList = new ArrayList<>();
		List<String> WhereList = new ArrayList<>();
		UpdateModel updateModel = new UpdateModel();
		updateModel.setValueRecordList(updateValueCSVParser.getRecords());
		updateModel.setWhereValueRecordList(whereClauseCSVParser.getRecords());
		updateModel.setTableName("db_data.dbo.user");

		updateColumnList.add("COLHEADER1");
		updateColumnList.add("COLHEADER2");
		updateColumnList.add("COLHEADER3");
		updateModel.setColumnList(updateColumnList);

		WhereList.add("whereHeader1");
		WhereList.add("whereHeader2");
		WhereList.add("whereHeader3");
		updateModel.setWhereList(WhereList);
		return updateModel;

	}
}
