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

	public static String fileName = "FirstDemo.xlsx";

	@DataProvider()
	static public Object[][] getTestData() throws Exception {

		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "//src//main//resources//" + fileName;
		ExcelUtils excelUtils = new ExcelUtils(excelPath, "Sheet4");
		log.info("Total Rows: {} ", excelUtils.excelGetRows());
		String[][] data = excelUtils.getExcelData();
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
