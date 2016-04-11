package com.google.tests;

import com.google.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import java.util.ArrayList;

/**
 * @author ajay.kg created on 06/04/16.
 */
@Slf4j
public class SampleDataFactory {

	public static String fileName = "Test.xlsx";

	@DataProvider()
	static public Object[][] getTestData() throws Exception {

		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "//src//main//resources//" + fileName;
		ExcelUtils excelUtils = new ExcelUtils(excelPath, "Sheet2");
		log.info("Total Rows: {} ", excelUtils.excelGetRows());
		// String[][] data = excelUtils.getExcelData();
		String[][] data = excelUtils.readExcelRows(1, 4);
		return data;
	}

	@Factory(dataProvider = "getTestData")
	public Object[] createInstance(String operation, String num1, String num2, String num3) {
		ArrayList<Object> ar = new ArrayList<Object>();
		ar.add(new SampleTest(num1, num2, num3, operation));
		Object[] res = new Object[ar.size()];
		res = ar.toArray();
		return res;
	}
}
