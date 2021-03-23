package com.java.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class SelectService extends CRUDUtils implements CRUDInterface {

	public static void sample() throws IOException {
		SaveModel saveModel = sampleSetValue();
		System.out.println(preparingStatement(saveModel));
	}

	private static String preparingStatement(SaveModel saveModel) {
		UpdateModel updateModel = new UpdateModel();
		InsertModel insertModel = new InsertModel();
		SelectModel selectModel = new SelectModel();

		updateModel = saveModel.getUpdateModel();
		insertModel = saveModel.getInsertModel();
		selectModel = saveModel.getSelectModel();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < saveModel.getSelectModel().getValueRecordList().size(); i++) {
			selectModel.setValueRecord(selectModel.getValueRecordList().get(i));
			selectModel.setWhereValueRecord(selectModel.getValueRecordList().get(i));

			updateModel.setValueRecord(updateModel.getValueRecordList().get(i));
			updateModel.setWhereValueRecord(updateModel.getWhereValueRecordList().get(i));

			insertModel.setValueRecord(insertModel.getValueRecordList().get(i));

			sb.append(insertOrUpdateStatement(saveModel));
		}
		return sb.toString();
	}

	private static String insertOrUpdateStatement(SaveModel saveModel) {
		String checkExistString = executeStatement(saveModel.getSelectModel());
		String insertString = InsertService.executeStatement(saveModel.getInsertModel());
		String checkExistElseString = "ELSE";
		String updateString = UpdateService.executeStatement(saveModel.getUpdateModel());
		return concatStringWithBreakLine(checkExistString, insertString, checkExistElseString, updateString);
	}

	public static String executeStatement(CRUDModel crudModel) {
		String selectString = new String();
		if (!ObjectUtils.isEmpty(crudModel.getColumnList())) {
			selectString = listToString(crudModel.getColumnList());
		}
		String whereString = convertWhereListToString(crudModel.getWhereList(), crudModel.getWhereValueRecord());
		crudModel.setColumnString(selectString);
		crudModel.setWhereString(whereString);
		return buildExecuteString(crudModel);
	}

	private static String buildExecuteString(CRUDModel crudModel) {
		StringBuilder selectExistStringBuilder = new StringBuilder();
		selectExistStringBuilder.append("IF EXISTS(");
		selectExistStringBuilder.append("SELECT ");
		selectExistStringBuilder.append(crudModel.getColumnString());
		selectExistStringBuilder.append(" FROM ");
		if (StringUtils.isNotBlank(crudModel.getTableName())) {
			selectExistStringBuilder.append(crudModel.getTableName());
		}
		selectExistStringBuilder.append(" WHERE ");
		selectExistStringBuilder.append(crudModel.getWhereString());

		selectExistStringBuilder.append(")");
		return selectExistStringBuilder.toString();
	}

	private static SaveModel sampleSetValue() throws IOException {
		List<CSVRecord> csvRecords = whereClauseCSVParser.getRecords();
		SaveModel saveModel = new SaveModel();

		// select

		SelectModel selectModel = sampleSelectModel(csvRecords);
		// update
		UpdateModel updateModel = sampleUpdateModel(csvRecords);

		// insert
		InsertModel insertModel = sampleInsertModel(csvRecords);

		saveModel.setSelectModel(selectModel);
		saveModel.setUpdateModel(updateModel);
		saveModel.setInsertModel(insertModel);
		return saveModel;
	}

	private static SelectModel sampleSelectModel(List<CSVRecord> csvRecords) throws IOException {

		SelectModel selectModel = new SelectModel();
		List<String> selectColumnList = new ArrayList();
		List<String> whereClauseList = new ArrayList<>();
		whereClauseList.add("whereHeader1");
		whereClauseList.add("whereHeader2");
		whereClauseList.add("whereHeader3");

		selectColumnList.add("columnheader1");
		selectColumnList.add("columnheader2");
		selectColumnList.add("columnheader2");
		selectModel.setColumnList(selectColumnList);

		selectModel.setWhereList(whereClauseList);

		selectModel.setValueRecordList(selectValueCSVParser.getRecords());
		selectModel.setWhereValueRecordList(csvRecords);
		selectModel.setTableName("db_data.dbo.user");
		return selectModel;
	}

	private static UpdateModel sampleUpdateModel(List<CSVRecord> csvRecords) throws IOException {

		UpdateModel updateModel = new UpdateModel();
		List<String> updateColumnList = new ArrayList<>();
		List<String> whereClauseList = new ArrayList<>();
		whereClauseList.add("whereHeader1");
		whereClauseList.add("whereHeader2");
		whereClauseList.add("whereHeader3");

		updateModel.setValueRecordList(updateValueCSVParser.getRecords());
		updateModel.setWhereValueRecordList(csvRecords);
		updateModel.setTableName("db_data.dbo.user");
		updateColumnList.add("COLHEADER1");
		updateColumnList.add("COLHEADER2");
		updateColumnList.add("COLHEADER3");
		updateModel.setColumnList(updateColumnList);
		updateModel.setWhereList(whereClauseList);
		return updateModel;
	}

	private static InsertModel sampleInsertModel(List<CSVRecord> csvRecords) throws IOException {

		InsertModel insertModel = new InsertModel();
		List<String> insertColumnList = new ArrayList();
		insertColumnList.add("columnheader1");
		insertColumnList.add("columnheader2");
		insertColumnList.add("columnheader2");
		insertModel.setColumnList(insertColumnList);
		insertModel.setValueRecordList(insertValueCSVParser.getRecords());
		insertModel.setTableName("db_data.dbo.user");

		return insertModel;
	}
}
