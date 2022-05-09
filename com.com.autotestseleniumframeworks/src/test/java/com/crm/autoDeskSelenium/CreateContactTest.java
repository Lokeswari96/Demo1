package com.crm.autoDeskSelenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fil = new FileInputStream("./src/test/resources/Exceldata/Data.xlsx");
		Workbook book = WorkbookFactory.create(fil);
		DataFormatter  format=new DataFormatter();
		String contactName = format.formatCellValue(book.getSheet("Sheet2").getRow(1).getCell(3));
		System.out.println(contactName+" ");
		
		FileInputStream fis =new FileInputStream("./src/test/resources/commonData/commondata.properties");
		Properties pro_obj = new Properties();
		pro_obj.load(fis);
		
		String browser = pro_obj.getProperty("browser");
		String url = pro_obj.getProperty("url");
		String username = pro_obj.getProperty("username");
		String password = pro_obj.getProperty("password");
		
		//run time polymorphism
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		       driver = new FirefoxDriver();
		       System.out.println("launched browser is" + browser);
		}
		else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		       driver = new ChromeDriver();
		       System.out.println("launched browser is" + browser);
		}else {
			System.out.println("specify avalid browser");
		}
		 driver.get(url);
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      driver.findElement(By.name("user_name")).sendKeys(username);
	      driver.findElement(By.name("user_password")).sendKeys(password);
	      driver.findElement(By.id("submitButton")).click();
         driver.findElement(By.linkText("Contacts")).click();
	      
	      driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	      
	      Random r = new Random();
	      int randomNum = r.nextInt(1000);
	      contactName = contactName + randomNum;
	      
	     driver.findElement(By.name("lastname")).sendKeys(contactName);
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
	    WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    Actions act = new Actions(driver);
	    act.moveToElement(logout).perform();
	    
	    driver.findElement(By.linkText("Sign Out")).click();
	    driver.quit();
	}

}
