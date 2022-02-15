package com.DDdemo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.DDdemo.base.TestBase;
import com.DDdemo.pages.HomePage;
import com.DDdemo.pages.JavaPage;

public class HomePageTest extends TestBase {

	JavaPage javapage;
	HomePage homepage;
	
	static boolean assertVerify=false;
	public static String TestDataSheetPath = ".\\src\\main\\java\\com\\DDdemo\\testdata\\datavalues.xlsx";
	String sheetName="values";
	int rowNum=2;

	
	public HomePageTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public void setup() {
		initialization();
		homepage = new HomePage();

	}

	@Test(priority = 1)
	public void writeFieldDDfmk() {
		Assert.assertTrue(homepage.writeonField(TestDataSheetPath, sheetName, rowNum));
		
	}

	@Test(priority = 2)
	public void ClickActionTest1() throws InterruptedException {
		Assert.assertTrue(homepage.clicksearch());
			}
	
	@Test(priority = 3)
	public void ClickActiononImgTest2() throws InterruptedException {
		Assert.assertTrue(homepage.clickImg());
			}
	
	@Test(priority = 4)
	public void ClickActionLinkTest3() throws InterruptedException {
		Assert.assertTrue(homepage.clickLinkJava());
			}
	
	@Test(priority = 5)
	public void verifyTest() throws InterruptedException {
		Assert.assertTrue(homepage.verifyText());
			}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
