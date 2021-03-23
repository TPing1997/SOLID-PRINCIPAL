package com.java.csv;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class CRUDUtils extends CSVParserUtils {
	protected static String concatStringWithBreakLine(String... mulStr) {
		StringBuilder sb = new StringBuilder();
		for (String str : mulStr) {
			sb.append(str);
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	protected static String CSVRecordToString(CSVRecord columnList) {
		StringBuilder sb = new StringBuilder();
		Integer columnSize = columnList.size();
		for (int i = 0; i < columnSize; i++) {
			sb.append(concatString(columnList.get(i), columnSize, i));
		}
		return sb.toString();
	}


	protected static String listToString(List<String> columnList) {
		StringBuilder sb = new StringBuilder();
		Integer columnSize = columnList.size();
		for (int i = 0; i < columnSize; i++) {
			sb.append(concatString(columnList.get(i), columnSize, i));
		}
		return sb.toString();
	}

	protected static String concatString(String column, int columnSize, int i) {

		if (i < columnSize - 1) {
			return column + ",";
		}
		return column;
	}

	protected static String convertColumnListToString(List<String> columnsList, CSVRecord columnsValueList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < columnsList.size(); i++) {

			sb.append(columnsList.get(i));
			sb.append("=");
			sb.append(columnsValueList.get(i));
			if (i < columnsList.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	protected static String convertWhereListToString(List<String> columnsList, CSVRecord columnsValueList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < columnsList.size(); i++) {

			sb.append(columnsList.get(i));
			sb.append("=");
			sb.append(columnsValueList.get(i));
			if (i < columnsList.size() - 1) {
				sb.append(" AND ");
			}
		}
		return sb.toString();
	}
}
