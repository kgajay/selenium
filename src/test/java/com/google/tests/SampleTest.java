package com.google.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * @author ajay.kg created on 06/04/16.
 */
@Slf4j
@Test(suiteName = "SampleTest")
public class SampleTest {

	private String operation;
	private String num1;
	private String num2;
	private String num3;

	public SampleTest() {

	}

	public SampleTest(String num3, String num2, String num1, String operation) {
		this.num3 = num3;
		this.num2 = num2;
		this.num1 = num1;
		this.operation = operation;
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before class executed");
	}

	@Test
	public void sampleTestMethod() {

		log.info("SAMPLE TEST: {}, NUM1: {}, NUM2: {}, NUM3: {}", operation, num1, num2, num3);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		// driver.quit();
//		if (!result.isSuccess()) {
//			log.error("FAILED-DRIVER-OPERATOR-RESET:::: driver: ");
//		} else {
//			log.error("SUCCESS-DRIVER-OPERATOR-RESET:::: driver: ");
//		}
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After class executed");
	}
}
