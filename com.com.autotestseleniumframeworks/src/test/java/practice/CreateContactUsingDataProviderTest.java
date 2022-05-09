package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactUsingDataProviderTest {
@Test(dataProvider = "getdata")
public void CreateContact(String LastName, String MobileNo){
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get("http://localhost:8888/");

	  driver.findElement(By.name("user_name")).sendKeys("admin");
      driver.findElement(By.name("user_password")).sendKeys("admin");
      driver.findElement(By.id("submitButton")).click();
	
      driver.findElement(By.linkText("Contacts")).click();
       driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
       driver.findElement(By.name("lastname")).sendKeys(LastName);
     driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys(MobileNo);
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	
}
@DataProvider
public Object[][] getdata(){
	Object[][] objArray = new Object[5][2];
	  
	objArray[0][0]="TYSS";
	objArray[0][1]="9773462727";
	
	objArray[1][0]="Infosys";
	objArray[1][1]="8896253421";
	
	objArray[2][0]="TCS";
	objArray[2][1]="8736224654";
	
	objArray[3][0]="Loki123";
	objArray[3][1]="7823562362";
	
	objArray[4][0]="Adithy123";
	objArray[4][1]="9713363737";
	
	return objArray;
}
}
