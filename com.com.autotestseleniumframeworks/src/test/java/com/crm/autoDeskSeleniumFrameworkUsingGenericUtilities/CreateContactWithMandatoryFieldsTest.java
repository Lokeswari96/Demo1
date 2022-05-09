package com.crm.autoDeskSeleniumFrameworkUsingGenericUtilities;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebdriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithMandatoryFieldsTest {

	public static void main(String[] args) throws Throwable {

		// object creation for utilities
		FileUtility flib= new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		WebdriverUtility wlib= new WebdriverUtility();
		JavaUtility jlib = new JavaUtility();
		
		// getting common data from property file
		String browser = flib.getPropertykeyValue("browser");
		String url = flib.getPropertykeyValue("url");
		String username = flib.getPropertykeyValue("username");
		String password = flib.getPropertykeyValue("password");
		
		// getting the data from excel sheet
		String contactname=elib.getDataFromExcel("Sheet7", 1, 2)+jlib.getRandomNumber();
		
		WebDriver driver = null;
		//launching the browser
	if(browser.equalsIgnoreCase("chrome")){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();			
		}else if(browser.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else{
		System.out.println("Browser id not supported");
		}
		
		
		driver.manage().window().maximize();

		wlib.waitForPageToLoad(driver);
		driver.get(url);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
	      driver.findElement(By.name("user_password")).sendKeys(password);
	      driver.findElement(By.id("submitButton")).click();

	      driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	      
	      jlib.getRandomNumber();
	      
	     driver.findElement(By.name("lastname")).sendKeys(contactname);
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
      
	    WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    Actions act = new Actions(driver);
	    act.moveToElement(logout).perform();
	    
	    driver.findElement(By.linkText("Sign Out")).click();
	    driver.quit();
		

	}

}
