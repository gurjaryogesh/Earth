package com.test;

import org.testng.annotations.Test;

import com.action.LoginAction;
import com.relevantcodes.extentreports.LogStatus;

public class Login extends BaseTemplate{
	
	@Test
	public void testLogin(){		
		logger.log(LogStatus.PASS, "User login in to the system");
		LoginAction action = new LoginAction();
		action.testCorrectLogin();
		logger.log(LogStatus.PASS, "User successfully logged in to the system");
		verifyTest(true, "User successfully entered");
	}
	
	@Test
	public void testLogin2(){		
		logger.log(LogStatus.PASS, "User login in to the system");
		LoginAction action = new LoginAction();
		action.testCorrectLogin();
		
		//verifyTest(false, "User successfully entered");
		verifyTest("sahil1","sahil","Not Matched");
	}

}
