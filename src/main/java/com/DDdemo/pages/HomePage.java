package com.DDdemo.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DDdemo.base.TestBase;
import com.DDdemo.util.TestUtil;

public class HomePage extends TestBase {
	// Page Factory Or Object Repo:
	// WebElements - Input elements:Textfields/Textarea/Checkboxs/Dropdown
	// fields/Date fields:
	@FindBy(name = "search")
	WebElement wtsearchField;
	
	@FindBy(xpath = "//*[@id=\"___gcse_0\"]/div/div/form/table/tbody/tr/td[2]/button")
	WebElement searchImg;
	
	@FindBy(xpath = "//*[@id=\"headerpage\"]/table/tbody/tr/td/div[1]/a/img")
	WebElement imgjpoint;
	
	@FindBy(xpath = "//*[@id=\"link\"]/div/ul/li[3]/a")
	WebElement lkJava;

	@FindBy(xpath = "//*[@id=\"menu\"]/a")
	WebElement textJavatp;

	// Other WebElements

	public HomePage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	public boolean writeonField(String TestDataSheetPath, String sheetName, int rowNum) {
		String value = TestUtil.getExcelDataByColumnName(TestDataSheetPath, sheetName, "value1", rowNum);
		wtsearchField.sendKeys(value);
		return true;
	}
	public boolean writeonFieldDDapproach(String dataval) throws InterruptedException {
		wtsearchField.clear();
		Thread.sleep(3000);
		wtsearchField.sendKeys(dataval);
		return true;
	}
	
	public boolean clicksearch() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		searchImg.click();
		return true;
	}
	
	public boolean clickImg() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		imgjpoint.click();
		return true;
	}

	public boolean clickLinkJava() {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		lkJava.click();
		return true;
	}
	public boolean verifyText() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		boolean isdisplayed=false;
		isdisplayed=textJavatp.isDisplayed();
		return isdisplayed;
	}
	// Actions/Features:
	
	// Method to validate Sign In Page is displayed
	//validatePgmpDashboardHomepageTitle

	

}

