package com.test;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utility.ReadProperties;
import com.utility.StartUpInitilize;

public class BaseTemplate {
	WebDriver driver;
	ExtentReports report = null;
	ExtentTest logger = null;
	
	@BeforeMethod
	@Parameters({"browser","envName"})
	public void launchBrowser(String browser, String envName,Method method){		
		logger = report.startTest(method.getName());
		StartUpInitilize initilize = new StartUpInitilize();
		ReadProperties readProp = new ReadProperties();		
		initilize.setEnvName(envName);
		System.out.println("Get Env Name : "+initilize.getEnvName());

		switch(browser){		
		case "chrome" : 			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
			driver = new ChromeDriver();			
			break;			
			
		case "firefox" :
			System.setProperty("webdriver.chrome.driver", "F:/Auto/Driver/chromedriver_win32/chromedriver.exe");
			driver = new FirefoxDriver();
			break;
			
		default :
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
			driver = new ChromeDriver();		
		}	
				
		initilize.setDriver(driver);
		System.out.println("Get Driver Name : "+initilize.getDriver());
		driver.manage().window().maximize();
		driver.get(readProp.getProperties("url"));		
	}
	
	
	@BeforeSuite
	public void initTestMethods() {
		report = new ExtentReports (System.getProperty("user.dir") +"/test-output/ExtentReport.html", true);
	}
	
	@AfterSuite
	public void cleanTestMethods() {
		report.flush();
	}
	
	@AfterMethod
	public void cleanUp(){
		report.endTest(logger);
		System.out.println("-----After Test----------");
		driver.close();	
	}
	
	public void verifyTest(boolean isPresent, String Message) {		
		if(isPresent) {
			logger.log(LogStatus.PASS, Message);
		}else {
			logger.log(LogStatus.FAIL, Message);
		}		
		Assert.assertTrue(isPresent,Message);
	}
	
	public void verifyTest(String actual,String expected, String Message) {
		boolean isTrue = false;
		if(actual.equals(expected)) {
			logger.log(LogStatus.PASS, Message);
			isTrue = true;
		}else {
			logger.log(LogStatus.FAIL, Message);
		}		
		Assert.assertTrue(isTrue,Message);
	}
	
	public void enterText(WebElement webElement,String text){
		try{
			webElement.sendKeys(text);
		}catch(Exception e){			
			e.printStackTrace();
			verifyTest(false,"Webmeent not found");
		}		
	}
	
	public void enterText(String xpath,String text){
		try{
			driver.findElement(By.xpath(xpath)).sendKeys(text);
		}catch(Exception e){			
			e.printStackTrace();
			verifyTest(false,"Webmeent not found");
		}		
	}
	
	public void enterText(By xpath,String text){
		try{
			driver.findElement(xpath).sendKeys(text);
		}catch(Exception e){			
			e.printStackTrace();
			verifyTest(false,"Webmeent not found");
		}
		
	}
}
