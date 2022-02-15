/**
 * 
 */
package com.DDdemo.util;

/**
 * @author VijayalakshmiPayagon
 *
 */
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DDdemo.base.TestBase;

public class Driver extends TestBase  {

	//public static WebDriver driver;
	private Set<String> afterHandles;
	private Set<String> beforeHandles;
	public static final String MAXIMIZE_BROWSER_WINDOW = "if (window.screen) {window.moveTo(0, 0);window.resizeTo(window.screen.availWidth,window.screen.availHeight);};";
	WebElement loTestObj = null;

	private static void setDriverInstance(WebDriver webDriver)

	{
		driver = webDriver;

	}

	public WebDriver getDriverInstance() {
		return driver;
	}

	private Driver(WebDriver driver) {
		this.driver = driver;
	}

	public static void initializeDriver(WebDriver driver) {
		setDriverInstance(driver);
	}

	public static void setActiveWindow() {
		int count = 0;
		for (String handle : driver.getWindowHandles()) { // Last
			if (count == driver.getWindowHandles().size() - 1) {
				driver.switchTo().window(handle);
				break;
			}
			count++;
		}
	}

	public static void setDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void maximize() {
		executeJavascript(driver, MAXIMIZE_BROWSER_WINDOW);
	}

	private static Object executeJavascript(WebDriver driver, String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(script);
	}

	public String getAttribute(String locator, String attribute) {
		return driver.findElement(By.id(locator)).getAttribute(attribute);
	}

	public String getLocator(String key) {
		String locator = null;
		return locator;
	}

	public void inputKeys(String locator, String text) {

		// element.type(text);
		driver.findElement(By.id(locator)).sendKeys(text);
	}

	public void setSpeed(String speed) {
		// driver.setSpeed(speed);
	}

	public void click(WebElement element) {

		Actions builder = new Actions(driver);
		builder.click(element);
		Action click = builder.build();
		click.perform();

	}

	public void mousehoverclick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		element.click();
	}

	public void webDriverWaitClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 4);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();

	}

	public void doubleClick(WebElement element) {

		Actions builder = new Actions(driver);
		builder.doubleClick(element);
		Action doubleClick = builder.build();
		doubleClick.perform();

	}

	public void selectDropDown(String selectString) {

		WebElement select = driver.findElement(By.xpath("//select"));
		List<WebElement> allOptions = select.findElements(By.tagName("option"));
		for (WebElement option : allOptions) {
			if (option.getAttribute("value").equalsIgnoreCase(selectString)) {
				System.out.println(String.format("Value is: %s", option.getAttribute("value")));
				option.click();
				break;
			}
		}
	}

	public boolean selectDropDownWithNoLabel(String selectString) {

		List<WebElement> selectlist = driver.findElements(By.xpath("//select"));
		for (WebElement select : selectlist) {
			List<WebElement> allOptions = select.findElements(By.tagName("option"));
			for (WebElement option : allOptions) {
				if (option.getAttribute("text").equalsIgnoreCase(selectString)) {
					System.out.println(String.format("Value is: %s", option.getAttribute("value")));
					option.click();
					return true;
				}
			}
		}
		return false;

	}

	public void selectDropDownByVisibleText(String selectString) {
		Select select = new Select(driver.findElement(By.xpath("//select")));
		select.deselectAll();
		select.selectByVisibleText(selectString);
	}

	public void selectFrame(String frame) {

		if (Driver.driver instanceof FirefoxDriver || Driver.driver instanceof InternetExplorerDriver) {
			driver.switchTo().frame(frame);
		} else {
			WebElement elementFrame = null;
			elementFrame = driver.findElement(By.id(frame));
			driver.switchTo().frame(elementFrame);
			return;
		}

	}

	public static void selectFrame(WebElement frame) {
		driver.switchTo().frame(frame);
	}

	public Alert selectAlert() {

		return driver.switchTo().alert();
	}

	public void selectWindow(String window) {

		driver.switchTo().window(window);
	}

	public String getText(String query) {
		return driver.findElement(By.id(query)).getText();
	}

	public String getValue(String locator) {
		return driver.findElement(By.id(locator)).getText();
	}

	public void checkRadioButton(String locator) {
		WebElement checkRadio = driver.findElement(By.id(locator));
		checkRadio.click();
	}

	public void checkCheckBox(String locator) {
		WebElement checkCheckBox = driver.findElement(By.id(locator));
		checkCheckBox.click();
	}

	/**
	 * This will return the Top window.
	 * 
	 * @return
	 */
	public String getTopWindowTitle() {
		this.getTopWindowHandle();
		return driver.getTitle();
	}

	public String getCurrentWindowTitle() {
		return driver.getTitle();
	}

	public void draganddrop(String source, String target) {

		WebElement element = driver.findElement(By.name(source));

		WebElement target1 = driver.findElement(By.name(target));

		(new Actions(driver)).dragAndDrop(element, target1).perform();
	}

	public void setAfterActionWindowHandles() {
		// this.sleep(1000);
		afterHandles = driver.getWindowHandles();

	}

	public void setBeforeActionWindowHandles() {
		beforeHandles = driver.getWindowHandles();

	}

	public void getTopWindowHandle() {

		if (beforeHandles != null && driver.getWindowHandles().size() > beforeHandles.size()) {
			if (afterHandles != null) {
				afterHandles.removeAll(beforeHandles);

				// Switch to the new window

				String newWindowHandle = afterHandles.iterator().next();

				driver.switchTo().window(newWindowHandle);
			}

		} else {
			int count = 0;
			for (String handle : driver.getWindowHandles()) { // Last
				if (count == driver.getWindowHandles().size() - 1) {
					driver.switchTo().window(handle);
					break;
				}
				count++;
			}
		}
	}

	/**
	 * Pause for X milliseconds.
	 * 
	 * @param iTimeInMillis
	 */
	public static void pause(final int iTimeInMillis) {
		try {
			Thread.sleep(iTimeInMillis);
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public ArrayList<String> getBrowserTitleList() {
		return null;

	}

	public int getBrowserCount() {
		return getBrowserTitleList().size();
	}

	public void captureScreenshot(String imageFileName) throws IOException {
		this.setBeforeActionWindowHandles();
		this.getTopWindowHandle();
		driver.getTitle();

		File scrFile = (((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
		FileUtils.copyFile(scrFile, new File(imageFileName));
	}

	public void createScreenCaptureJPEG(String filename) {
		try {
			BufferedImage img = getScreenAsBufferedImage();
			File output = new File(filename);
			ImageIO.write(img, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedImage getScreenAsBufferedImage() {
		BufferedImage img = null;
		try {
			Robot r;
			r = new Robot();
			Toolkit t = Toolkit.getDefaultToolkit();
			Rectangle rect = new Rectangle(t.getScreenSize());
			img = r.createScreenCapture(rect);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return img;
	}

	public boolean isVisible(String locator) {
		WebElement element = driver.findElement(By.id(locator));
		return element.isDisplayed();
	}

	public boolean isElementEnable(String locator) {
		WebElement element = driver.findElement(By.id(locator));
		return element.isEnabled();
	}

	public boolean isSelected(String locator) {
		WebElement element = driver.findElement(By.id(locator));
		return element.isSelected();
	}

	public static void explicitWait(final String dynamicElement, long seconds) {
		WebElement myDynamicElement = (new WebDriverWait(driver, seconds)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id(dynamicElement));
			}
		});
	}

	public static void explicitWait(WebElement dynamicElement, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(dynamicElement));	
	}

	public void implicitWait(long duration) {
		driver.manage().timeouts().implicitlyWait(duration, MILLISECONDS);
	}

	public void keyPress(String locator, String nextKey) {

	}

	public void popupDialogue() {
		Alert alert = driver.switchTo().alert();
	}

	public WebElement findElement(By arg0) {
		return driver.findElement(arg0);
	}

	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return driver.findElements(arg0);
	}

	public void close() {
		int count = 0;
		for (String handle : driver.getWindowHandles()) { // Last
			if (count == driver.getWindowHandles().size() - 1) {
				driver.switchTo().window(handle).close();
				break;
			}
			count++;
		}

	}

	public boolean isElementPresent(String byId, Boolean displayCustomMessage, String customMessage) {
		try {

			driver.findElement(By.id(byId));

		} catch (org.openqa.selenium.NoSuchElementException Ex) {
			if (displayCustomMessage) {
				if (!customMessage.equals("")) {
					System.out.print(customMessage);
				}
			} else {
				System.out.println("Unable to locate Element: " + byId);
			}
			return false;
		}
		return true;
	}
	public void initializeElements() {
	PageFactory.initElements(driver, this);
	}
}
