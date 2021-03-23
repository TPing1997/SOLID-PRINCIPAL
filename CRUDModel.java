package com.java.csv;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class CRUDModel {

	String tableName;
	List<CSVRecord> valueRecordList;
	CSVRecord valueRecord;
	List<String> columnList;
	String columnString;
	String valueString;
	List<CSVRecord> whereValueRecordList;
	CSVRecord whereValueRecord;
	List<String> whereList;
	String whereString;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<CSVRecord> getValueRecordList() {
		return valueRecordList;
	}

	public void setValueRecordList(List<CSVRecord> valueRecordList) {
		this.valueRecordList = valueRecordList;
	}

	public CSVRecord getValueRecord() {
		return valueRecord;
	}

	public void setValueRecord(CSVRecord valueRecord) {
		this.valueRecord = valueRecord;
	}

	public List<String> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}

	public String getColumnString() {
		return columnString;
	}

	public void setColumnString(String columnString) {
		this.columnString = columnString;
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	public List<CSVRecord> getWhereValueRecordList() {
		return whereValueRecordList;
	}

	public void setWhereValueRecordList(List<CSVRecord> whereValueRecordList) {
		this.whereValueRecordList = whereValueRecordList;
	}

	public CSVRecord getWhereValueRecord() {
		return whereValueRecord;
	}

	public void setWhereValueRecord(CSVRecord whereValueRecord) {
		this.whereValueRecord = whereValueRecord;
	}

	public List<String> getWhereList() {
		return whereList;
	}

	public void setWhereList(List<String> whereList) {
		this.whereList = whereList;
	}

	public String getWhereString() {
		return whereString;
	}

	public void setWhereString(String whereString) {
		this.whereString = whereString;
	}

}
