package com.locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.StartUpInitilize;

public class LoginLocators {
	
	public LoginLocators(){
		StartUpInitilize initilize = new StartUpInitilize();
		PageFactory.initElements(initilize.getDriver(), this);		
	}
	
	@FindBy(id="login-username")
	public WebElement username;	
	
	public String user = "//";
	
	public By userNew = By.xpath("//");
	

}
