package com.crm.AutotestSeleniumUsingTestNG;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.ContactInformationTest;
import com.crm.autodesk.ObjectRepository.ContactsTest;
import com.crm.autodesk.ObjectRepository.CreateContactTest;
import com.crm.autodesk.ObjectRepository.CreateContactwithOrgnTest;
import com.crm.autodesk.ObjectRepository.CreateNewOrgTest;
import com.crm.autodesk.ObjectRepository.CreateOrganizationTest;
import com.crm.autodesk.ObjectRepository.HomePageTest;
import com.crm.autodesk.genericutility.BaseClass;

public class CreateContactWithOrganizationTest extends BaseClass{

	@Test
	public void CreateContactWIthOrg() throws Throwable{
		
		// getting the data from excel sheet
		String ContactName=elib.getDataFromExcel("Sheet9", 1, 3)+jlib.getRandomNumber();
		String OrgName=elib.getDataFromExcel("Sheet9", 1, 2)+jlib.getRandomNumber();
	    String ExpectedContactName="vtigeruser";
		
		HomePageTest Homepage = new HomePageTest(driver);
		Homepage.Organization();
		
		CreateOrganizationTest createOrg = new CreateOrganizationTest(driver);
		createOrg.Addicon();
		
		CreateNewOrgTest CreateOrg = new CreateNewOrgTest(driver);
		CreateOrg.OrganizationTextfield(OrgName);

		WebElement contact = Homepage.getContactsLink();
		wlib.waitForElementToBeClickAble(driver, contact);
		Homepage.Contacts();
				
		CreateContactTest contacts = new CreateContactTest(driver);
		contacts.AddIcon();
		
	    CreateContactwithOrgnTest contactorg = new CreateContactwithOrgnTest(driver);
		contactorg.lastNameTextField(ContactName);
		
		ContactsTest contPage= new ContactsTest(driver);
		contPage.searchTextfield(ExpectedContactName, driver);
		
		
			
		ContactInformationTest contactinfo = new ContactInformationTest(driver);
		String actualcon = contactinfo.ContactInformationLink();

	}
}
