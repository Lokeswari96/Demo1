package com.crm.autotestseleniumframeworkUsingPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.autodesk.ObjectRepository.ContactInformationTest;
import com.crm.autodesk.ObjectRepository.CreateContactTest;
import com.crm.autodesk.ObjectRepository.CreateContactwithLeadsourceTest;
import com.crm.autodesk.ObjectRepository.HomePageTest;
import com.crm.autodesk.ObjectRepository.LoginPageTest;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebdriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactwithLeadSourceDropdownTest {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver= null;
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
		String Contactname=elib.getDataFromExcel("Sheet5", 1, 2)+jlib.getRandomNumber();
		String dropdown = elib.getDataFromExcel("Sheet5", 1, 3);
		
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
		
	LoginPageTest Login = new LoginPageTest(driver);
	Login.login(username, password);

	HomePageTest Homepage = new HomePageTest(driver);
	Homepage.Contacts();

	CreateContactTest contactpage = new CreateContactTest(driver);
	contactpage.AddIcon();

	CreateContactwithLeadsourceTest leadsource = new CreateContactwithLeadsourceTest(driver);
	leadsource.LastnameTextField(Contactname, dropdown);
	
	ContactInformationTest info= new ContactInformationTest(driver);
    String actual = info.ContactInformationLink();

    //verification
	if(actual.contains(Contactname)){
   System.out.println("contact information created"+actual+"pass");
	 }else{
	System.out.println("contact information not created"+"fail");
	}
    Homepage.logout(driver);
	driver.close();

	}

}
