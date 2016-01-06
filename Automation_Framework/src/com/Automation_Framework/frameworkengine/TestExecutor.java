package com.Automation_Framework.frameworkengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class TestExecutor {

	WebDriver driver;
	ExcelLibrary lib;
	String scriptName;

	// String or_file =
	// ("user.dir")+"//src//com//Automation_Framework//properties//or.properties";
	// String config_file =
	// ("user.dir")+"//src//com//Automation_Framework//properties//config.properties";

	public void executeTest(String testScript) throws IOException,
			EncryptedDocumentException, InvalidFormatException {
		Properties config_prop = new Properties();
		FileInputStream config_ip = new FileInputStream(
				System.getProperty("user.dir")
						+ "//src//com//Automation_Framework//properties//config.properties");
		config_prop.load(config_ip);
		scriptName = testScript;
		driver.get(config_prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// creating object of Excel Library class to access all the methods and
		// member functions of ExcelLibrary
		lib = new ExcelLibrary();
		int numOfTestSteps = lib.getRowCount(scriptName);
		for (int i = 1; i <= numOfTestSteps; i++) {
			String action = lib.getExcelData(scriptName, i, 1);
			String elementIdMethod = lib.getExcelData(scriptName, i, 2);
			String locatorVal = getOrValue(lib.getExcelData(scriptName, i, 3));
			String data = lib.getExcelData(scriptName, i, 4);
			System.out.println(action + " -- " + elementIdMethod + " -- "
					+ locatorVal + " -- " + data);

			if (action.equals("type")) {
				type(elementIdMethod, locatorVal, data, i);
			} else if (action.equals("click")) {
				click(elementIdMethod, locatorVal, i);
			} else if (action.equals("select")) {
				select(elementIdMethod, locatorVal, data, i);
			} else if (driver.equals("alert")) {
				alert(data, i);
			} else if (driver.equals("verifyTitle")) {
				verifyTitle(data, i);
			} else if (driver.equals("verifyElement")) {
				verifyElement(elementIdMethod, locatorVal, i);
			}

		}
		driver.quit();
	}

	// Method to get data from or.properties file
	public String getOrValue(String loc) {
		String retVal = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					System.getProperty("user.dir")
							+ "//src//com//Automation_Framework//properties//or.properties"));
			retVal = prop.getProperty(loc);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(retVal);
		return retVal;
	}

	public void type(String elementIdMethod, String locatorVal, String data,
			int rowNum) {
		if (elementIdMethod.equals("name")) {
			try {
				driver.findElement(By.name(locatorVal)).sendKeys(data);
				lib.writeToExcel(scriptName, rowNum, 5, "PASS");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + data
						+ " into " + locatorVal + "  textbox");
			} catch (Exception e) {
				lib.writeToExcel(scriptName, rowNum, 5, "FAIL");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + locatorVal
						+ "  text box not found ");
			}

		} else if (elementIdMethod.equals("id")) {
			try {
				driver.findElement(By.id(locatorVal)).sendKeys(data);
				lib.writeToExcel(scriptName, rowNum, 5, "PASS");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + data
						+ " into " + locatorVal + "  textbox");
			} catch (Exception e) {
				lib.writeToExcel(scriptName, rowNum, 5, "FAIL");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + locatorVal
						+ "  text box not found ");
			}
		} else if (elementIdMethod.equals("class")) {
			try {
				driver.findElement(By.className(locatorVal)).sendKeys(data);
				lib.writeToExcel(scriptName, rowNum, 5, "PASS");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + data
						+ " into " + locatorVal + "  textbox");
			} catch (Exception e) {
				lib.writeToExcel(scriptName, rowNum, 5, "FAIL");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + locatorVal
						+ "  text box not found ");
			}
		} else if (elementIdMethod.equals("xpath")) {
			try {
				driver.findElement(By.xpath(locatorVal)).sendKeys(data);
				lib.writeToExcel(scriptName, rowNum, 5, "PASS");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + data
						+ " into " + locatorVal + "  textbox");
			} catch (Exception e) {
				lib.writeToExcel(scriptName, rowNum, 5, "FAIL");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + locatorVal
						+ "  text box not found ");
			}
		} else if (elementIdMethod.equals("css")) {
			try {
				driver.findElement(By.cssSelector(locatorVal)).sendKeys(data);
				lib.writeToExcel(scriptName, rowNum, 5, "PASS");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + data
						+ " into " + locatorVal + "  textbox");
			} catch (Exception e) {
				lib.writeToExcel(scriptName, rowNum, 5, "FAIL");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + locatorVal
						+ "  text box not found ");
			}
		} else if (elementIdMethod.equals("linkText")) {
			try {
				driver.findElement(By.linkText(locatorVal)).sendKeys(data);
				lib.writeToExcel(scriptName, rowNum, 5, "PASS");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + data
						+ " into " + locatorVal + "  textbox");
			} catch (Exception e) {
				lib.writeToExcel(scriptName, rowNum, 5, "FAIL");
				lib.writeToExcel(scriptName, rowNum, 6, "Typed  " + locatorVal
						+ "  text box not found ");
			}
		}
	}

	public void click(String elementIdMethod, String locatorVal, int rowNum) {
		if (elementIdMethod.equals("name")) {
			driver.findElement(By.name(locatorVal)).click();
		} else if (elementIdMethod.equals("id")) {
			driver.findElement(By.id(locatorVal)).click();
		} else if (elementIdMethod.equals("class")) {
			driver.findElement(By.className(locatorVal)).click();
		} else if (elementIdMethod.equals("xpath")) {
			driver.findElement(By.xpath(locatorVal)).click();
		} else if (elementIdMethod.equals("css")) {
			driver.findElement(By.cssSelector(locatorVal)).click();
		} else if (elementIdMethod.equals("linkText")) {
			driver.findElement(By.linkText(locatorVal)).click();
		}
	}

	// method to select drop down text by id, name and xpath.
	// if we want we can write one more if else for linkText, cssSelector...
	public void select(String elementIdMethod, String locatorVal, String data,
			int rowNum) {
		if (elementIdMethod.equals("name")) {
			WebElement ele = driver.findElement(By.name(locatorVal));
			Select dd = new Select(ele);
			String[] arr = data.split("=");
			if (arr[0].equals("ByVisibleText")) {
				List<WebElement> allOptions = dd.getOptions();
				for (int j = 0; j < allOptions.size(); j++) {
					if (allOptions.get(j).getText().equals(arr[1])) {
						dd.selectByVisibleText(arr[1]);
					}
				}
			} else if (arr[0].equals("ByValue")) {
				dd.selectByValue(arr[1]);
			} else if (arr[0].equals("ByIndex")) {
				dd.selectByIndex(Integer.parseInt(arr[1]));
			}
		} else if (elementIdMethod.equals("id")) {
			WebElement ele = driver.findElement(By.id(locatorVal));
			Select dd = new Select(ele);
			String[] arr = data.split("=");
			if (arr[0].equals("ByVisibleText")) {
				List<WebElement> allOptions = dd.getOptions();
				for (int j = 0; j < allOptions.size(); j++) {
					if (allOptions.get(j).getText().equals(arr[1])) {
						dd.selectByVisibleText(arr[1]);
					}
				}
			} else if (arr[0].equals("ByValue")) {
				dd.selectByValue(arr[1]);
			} else if (arr[0].equals("ByIndex")) {
				dd.selectByIndex(Integer.parseInt(arr[1]));
			}
		} else if (elementIdMethod.equals("xpath")) {
			WebElement ele = driver.findElement(By.xpath(locatorVal));
			Select dd = new Select(ele);
			String[] arr = data.split("=");
			if (arr[0].equals("ByVisibleText")) {
				List<WebElement> allOptions = dd.getOptions();
				for (int j = 0; j < allOptions.size(); j++) {
					if (allOptions.get(j).getText().equals(arr[1])) {
						dd.selectByVisibleText(arr[1]);
					}
				}
			} else if (arr[0].equals("ByValue")) {
				dd.selectByValue(arr[1]);
			} else if (arr[0].equals("ByIndex")) {
				dd.selectByIndex(Integer.parseInt(arr[1]));
			}
		}

	}

	public void alert(String data, int rowNum) {

	}

	public void verifyTitle(String data, int rowNum) {

	}

	public void verifyElement(String elementIdMethod, String locatorVal,
			int rowNum) {

	}

	// method to execute test cases in different browsers
	public void openBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"/Users/pavankumar/Desktop/Canada Automation/Selenium Jars/chromedriver");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"D:/Dev/Jars/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}

	}

	// Default Implicit wait method
	public void waitStmt() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// method to get all links present in web page and print in the console
	private static int linksCount = 0;
	private static String[] links = null;	
	public void get(String urlName) {
		driver.get(urlName);
		
		waitStmt();
		driver.manage().window().maximize();
		try {

			List<WebElement> all_Links = driver.findElements(By.tagName("a"));
			linksCount = all_Links.size();
			System.out
					.println("Total number of links present in Advertising blog are : "
							+ linksCount);
			links = new String[linksCount];
			System.out.println("List of links Available: ");
			System.out.println("--------------------");
			for (int i = 0; i < linksCount; i++) {
				links[i] = all_Links.get(i).getAttribute("href");
				System.out.println(all_Links.get(i).getAttribute("href"));
			}
			for(int i=0;i<linksCount;i++)
			{
			driver.navigate().to(links[i]);
			Thread.sleep(3000);
			}
			System.out.println("--------------------");
			//driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to take screenshot
	public void getscreenShot(String pageUrl) throws IOException {
		driver.get(pageUrl);
		waitStmt();
		driver.manage().window().maximize();
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		FileUtils
				.copyFile(
						scrFile,
						new File(
								"/Users/pavankumar/Desktop/Canada Automation/Automation_Framework/Screenshots/about-us.png"));
		driver.close();
	}

}
