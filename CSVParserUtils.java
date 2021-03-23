package com.java.csv;

import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CSVParserUtils {
	private static final URL CURR_CLASS_URL = Actions.class.getResource("");
	private static final Path CURR_CLASS_PATH = new File(CURR_CLASS_URL.getPath()).toPath();
	private static final String SAMPLE_CSV_FILE_PATH = CURR_CLASS_PATH + "/Sample.csv";
	private static final String WHERE_CLAUSE_CSV_FILE_PATH = CURR_CLASS_PATH + "/WhereClause.csv";
	protected static CSVParser insertValueCSVParser = sampleCSVParser();
	protected static CSVParser updateValueCSVParser = sampleCSVParser();
	protected static CSVParser selectValueCSVParser = sampleCSVParser();
	protected static CSVParser whereClauseCSVParser = whereClauseCSVParser();

	private static CSVParser sampleCSVParser() {
		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return csvParser;
	}

	private static CSVParser whereClauseCSVParser() {
		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(WHERE_CLAUSE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return csvParser;
	}
}
