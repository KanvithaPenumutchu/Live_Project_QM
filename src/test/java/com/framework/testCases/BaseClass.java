package com.framework.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import coreUtilities.ConfiguratorReader;
import coreUtilities.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	
	@BeforeMethod
	public static void beforeMethod() throws IOException
	{
		launchBrowser(ConfiguratorReader.getConfigValue("browser"));
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public static void afterMethod()
	{
		driver.quit();
	}
	
	
	//Screenshot
	public static String takeScreenShot() throws IOException
	{
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		String SrcFile=scrShot.getScreenshotAs(OutputType.BASE64);
		//Move image file to new destination
		/*File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		*/
		return SrcFile;
	}
	
	public static void launchBrowser(String browsername)
	{
		if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
	}
}
