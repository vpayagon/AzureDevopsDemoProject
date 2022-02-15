package com.DDdemo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.DDdemo.base.TestBase;
import com.DDdemo.pages.JavaPage;

public class JavaPageTest extends TestBase {

	JavaPage javapage;
	
	String sheetName="mywork";
	static boolean assertVerify=false;
	public static String TestDataSheetPath = ".\\src\\main\\java\\com\\DDdemo\\testdata\\request.xlsx";

	
	public JavaPageTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public void setup() {
		initialization();
		javapage = new JavaPage();

	}
	
	@Test(priority = 1)
	public void clickLJava() {
		Assert.assertTrue(javapage.clickLinkJava());
	}

	@Test(priority = 2)
	public void verifyText() throws InterruptedException {
		Assert.assertTrue(javapage.verifyJP());
	}

	@Test(priority = 3)
	public void clickLinkTest1() throws InterruptedException {
		Assert.assertTrue(javapage.clickLink1());
	}

	@Test(priority = 4)
	public void verifyTest1() throws InterruptedException {
		Assert.assertTrue(javapage.verifyText1());
	}
	
	@Test(priority = 5)
	public void clickLinkTest2() {
		Assert.assertTrue(javapage.clickLink2());
	}

	@Test(priority = 6)
	public void verifyTest2() throws InterruptedException {
		Assert.assertTrue(javapage.verifyText2());
	}
	
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
