package com.action;

import com.locator.LoginLocators;
import com.test.BaseTemplate;
import com.utility.ReadJson;

public class LoginAction extends BaseTemplate{
	
	public void testCorrectLogin(){
		LoginLocators locators = new LoginLocators();		
		enterText(locators.username,ReadJson.getJsonValue("$.AdminCredentials.username"));
	}

}
