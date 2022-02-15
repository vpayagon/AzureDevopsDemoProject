package com.DDdemo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;



public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	
	public TestBase() {
		// TODO Auto-generated constructor stub
		// Read properties file
		try {
			prop = new Properties();
			FileInputStream fip = new FileInputStream("./src/main/java/com/DDdemo/config/config.properties");
			prop.load(fip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		ChromeOptions options = new ChromeOptions();
		String browserName = prop.getProperty("browser");
		String applicationurl = prop.getProperty("applicationurl");
		//String applicationurl = System.getProperty("applicationurl");
		String osName = System.getProperty("os.name");
		if (browserName.equals("chrome")) {
			if (osName.equalsIgnoreCase("Windows 10")) {
				System.setProperty("webdriver.chrome.driver", "./src/main/java/com/DDdemo/resources/chromedriver.exe");
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				caps.setCapability(ChromeOptions.CAPABILITY, options);
				caps.setCapability("acceptInsecureCerts", true);
				driver = new ChromeDriver(caps);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else {
				System.setProperty("webdriver.chrome.driver", "./src/main/java/com/pgmp/resources/chromedriver");
				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
						"--ignore-certificate-errors", "--silent");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();

			}

		}
		// Automation execution on Fire fox browser
		else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./src/main/java/com/pgmp/resources/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		}

		else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "./src/main/java/com/pgmp/resources/msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}
		// uncomment this later for logging information
		// Detailed logging on the console - EventFiringWebDriver
		// e_driver = new EventFiringWebDriver(driver);
		// create object of EventListerHandler to register it with EventFiringWebDriver
		// eventListener = new WebEventListener();
		// e_driver.register(eventListener);
		// driver = e_driver;

		if (applicationurl.equalsIgnoreCase("Test")) {
			System.out.println("In test url");
			System.out.println("test url value=====" + prop.getProperty("testurl"));
			driver.get(prop.getProperty("testurl"));
			System.out.println("after driver get property");
			/*driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/

		} else if (applicationurl.equalsIgnoreCase("Prod")) {
			driver.get(prop.getProperty("produrl"));
		}
		if (applicationurl.equalsIgnoreCase("UAT")) {
			driver.get(prop.getProperty("uaturl"));
		}

	}

}
