/**
 * 
 */
package com.DDdemo.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.DDdemo.base.TestBase;

/**
 * @author VijayalakshmiPayagon
 *
 */
public class AppUtil extends TestBase{
//common method to click on Search Button
	public AppUtil() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="ibm-submit")
	static WebElement submit;
	
	
	@FindBy(name = "ibm-search")
	static WebElement search;
	
	@FindBy(xpath = "//*[@id=\"faces-result-0\"]/div/span[1]")
	static WebElement selectIBMid;
	
	
	//Actions/Features:
	//click on submit button
	public static void clicksubmit() {
		AppUtil.javascriptClick(submit);
	}
	
	//click on select Task radio button
	public static void selectTask(WebElement elem) {
		Driver.setActiveWindow();
		AppUtil.javascriptClick(elem);
	}

	//Perform mouse hover on web element 
	public static void mousehoveronElem(WebElement elem) {
		Actions action=new Actions(driver);
		action.moveToElement(elem).build().perform();	
	}
	//common method select value from drop down
	public static void selectValuefromdropdown(WebElement elem, String value) {
		Select select=new Select(elem);
		select.selectByVisibleText(value);
	}
	//Wait for page to load for 10 seconds
	public static void waitForPageToLoad() {
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}
	public static void waitForPageToLoadforSFA() {
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	}
	//click on button using Java script executor, helpful in case of IE browser
	public static void javascriptClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	//click on search button
		public static void clickSearch() {
			search.click();
		}
	//click on result field for IBM ids
		public static void selectIBMIdResult() {
			Driver.explicitWait(selectIBMid, 10);
			selectIBMid.click();
		}
		public static void scrollDownTillEnd() {
			JavascriptExecutor jse6 = (JavascriptExecutor) driver;
			jse6.executeScript("window.scrollBy(0,700)", "");
		}
		public static void scrollDown() {
			JavascriptExecutor jse6 = (JavascriptExecutor) driver;
			jse6.executeScript("window.scrollBy(0,250)", "");
		}
		public static boolean verifyText(WebElement Elem, String expectedString) {
			boolean flag=false;
			if(Elem.getText().equalsIgnoreCase(expectedString)) {
				flag= true;
			}
			else if(Elem.getText().contains(expectedString)) {
				flag= true;
			}
			return flag;
		}
		public static void  clickCalendar(WebElement label) {
			WebElement sibling = label.findElement(By.xpath("following-sibling::span"));
			List<WebElement> inputElem = sibling.findElements(By.tagName("input"));
			if (!(inputElem.size() > 0)) {
				sibling = sibling.findElement(By.xpath("follwing::node()"));
				inputElem = sibling.findElements(By.tagName("input"));
			}
			WebElement anchor = inputElem.get(0).findElement(By.xpath("following-sibling::a"));
			AppUtil.javascriptClick(anchor);

		}
		public static void  clickcalendarToday(WebElement elemtoday) {
			// AppUtil.javascriptClick(elemtoday);
			elemtoday.click();
		}
		public static void pageRegresh() throws InterruptedException {
			driver.navigate().refresh();
			Thread.sleep(3000);
		}

}
