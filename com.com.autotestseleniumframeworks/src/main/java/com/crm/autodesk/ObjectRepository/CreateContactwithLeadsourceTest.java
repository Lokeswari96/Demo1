package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebdriverUtility;

public class CreateContactwithLeadsourceTest extends WebdriverUtility{

	//initialization of web element
	public CreateContactwithLeadsourceTest(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	//declaration of web element
	@FindBy(name="lastname")
	private WebElement LastnameTextField;
	@FindBy(name="leadsource")
	private WebElement Leadsourcedropdown;
	@FindBy (xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	//getters method
	
	public WebElement getLastnameTextField() {
		return LastnameTextField;
	}
	public WebElement getLeadsourcedropdown() {
		return Leadsourcedropdown;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	//business logic
	/**
	 * this method will do create contact with lead source
	 * @param ContactName
	 * @param dropdown
	 */
	public void LastnameTextField(String ContactName, String dropdown){
	LastnameTextField.sendKeys(ContactName);
	select(Leadsourcedropdown,dropdown);
	saveButton.click();
}
}