package com.google.tests;

import com.google.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import java.util.ArrayList;

/**
 * @author ajay.kg created on 07/04/16.
 */
@Slf4j
public class GoogleHomePageTestFactory {

	public static String fileName = "Test.xlsx";

	@DataProvider()
	static public Object[][] getTestData() throws Exception {

		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "//src//main//resources//" + fileName;
		ExcelUtils excelUtils = new ExcelUtils(excelPath, "Sheet3");
		log.info("Total Rows: {} ", excelUtils.excelGetRows());
		int startRow = 1;
		int endRow = 1;
		String[][] data = excelUtils.readExcelRows(startRow, endRow);
		return data;
	}

	@Factory(dataProvider = "getTestData")
	public Object[] createInstance(String userName, String password) {
		ArrayList<Object> ar = new ArrayList<Object>();
		ar.add(new GoogleHomePageTest(userName, password));
		Object[] res = new Object[ar.size()];
		res = ar.toArray();
		return res;
	}

}
