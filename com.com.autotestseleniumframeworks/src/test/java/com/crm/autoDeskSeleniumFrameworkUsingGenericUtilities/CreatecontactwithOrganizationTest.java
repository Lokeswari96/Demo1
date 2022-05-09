package com.crm.autoDeskSeleniumFrameworkUsingGenericUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebdriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatecontactwithOrganizationTest {

	public static void main(String[] args) throws Throwable {
		JavaUtility jLib = new JavaUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		
		
		
		/* get randomdata*/
		int randomNum = jLib.getRandomNumber();
		
		/* read common data from properties file */
		
		String browser = fLib.getPropertykeyValue("browser");
		String url = fLib.getPropertykeyValue("url");
		String username = fLib.getPropertykeyValue("username");
		String password = fLib.getPropertykeyValue("password");
		
		/* read test data from Excel file*/
		
		
		
		String orgName = eLib.getDataFromExcel("contact", 4, 2)+ randomNum;
		
			String contactName = eLib.getDataFromExcel("contact", 4, 3)+randomNum;		
			
			
		/* launch the browser*/
			
		WebDriver driver = null;
	    
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();			
			}else if(browser.equals("firefox")){
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				
			}else{
			System.out.println("Browser id not supported");
			}
		 wLib.waitForPageToLoad(driver);
		 driver.get(url);
	     
		 driver.findElement(By.name("user_name")).sendKeys(username);
	      driver.findElement(By.name("user_password")).sendKeys(password);
	      driver.findElement(By.id("submitButton")).click();

	      // click organization link
	      driver.findElement(By.linkText("Organizations")).click();
	      
	     //click on "+" image
	      driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	    	      driver.findElement(By.name("accountname")).sendKeys(orgName);
	      
	      //click on save
			 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			 
			 WebElement headerwb = driver.findElement(By.className("dvHeaderText"));
			 wLib.waitForElementToBeClickAble(driver, headerwb);
			 
			
	     driver.findElement(By.linkText("Contacts")).click();
	     
	     driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	      
	     driver.findElement(By.name("lastname")).sendKeys(contactName);
	     
	     driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
	     
	     wLib.switchToWindow(driver, "Accounts");
	     
	    
	    driver.findElement(By.id("search_txt")).sendKeys(orgName);
	     driver.findElement(By.name("search")).click();
	     
	     driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	     
	    wLib.switchToWindow(driver, "Contacts");
	    		
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	    		 
		    wLib.mouseOverOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		    
		    
		    driver.findElement(By.linkText("Sign Out")).click();
		    driver.quit();

	}
	
	}


