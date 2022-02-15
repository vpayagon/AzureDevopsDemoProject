package com.DDdemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DDdemo.base.TestBase;



public class JavaPage extends TestBase {
	// Page Factory Or Object Repo:
	// WebElements - Input elements:Textfields/Textarea/Checkboxs/Dropdown
	// fields/Date fields:
	// Actions/Features:
	
	@FindBy(xpath = "//*[@id=\"link\"]/div/ul/li[3]/a")
	WebElement linkJava1;
	
	@FindBy(xpath = "//*[@id=\"city\"]/table/tbody/tr/td/h1")
	WebElement verifyJP;
	

	@FindBy(xpath = "//*[@id=\"menu\"]/div[2]/a[1]")
	WebElement link1;
	
	@FindBy(xpath = "//*[@id=\"what-is-java\"]")
	WebElement vtext1;

	@FindBy(xpath = "//*[@id=\"menu\"]/div[2]/a[3]")
	WebElement link2;

	@FindBy(xpath = "//*[@id=\"city\"]/table/tbody/tr/td/h1")
	WebElement vtext2;
	
	// Other WebElements

	public JavaPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	public boolean clickLinkJava() {
		linkJava1.click();
		return true;
		
	}
	
	public boolean verifyJP() {
		return verifyJP.isDisplayed();
		
	}
	
	public boolean clickLink1() throws InterruptedException {
		Thread.sleep(4000);
		link1.click();
		return true;
		
	}
	
	
	public boolean verifyText1() {
		return vtext1.isDisplayed();		
	}
	
	public boolean clickLink2() {
		link2.click();
		return true;
	}

	public boolean verifyText2() {
		return vtext2.isDisplayed();
		
	}
	// Method to validate Sign In Page is displayed
	//validatePgmpDashboardHomepageTitle
}

