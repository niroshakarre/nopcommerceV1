package com.nopcommerce.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageobject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws InterruptedException, IOException 
	{
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("User Provided");
		
		lp.setPassword(password);
		logger.info("Password Provided");
		
		lp.clicklogin();
		logger.info("Login clicked");

		if (driver.getTitle().equals("Dashboard / nopCommerce administration")) 
		{
			Thread.sleep(5000);
			
			lp.clicklogout();
			logger.info("Login Passed");
			Assert.assertTrue(true);
		}
		 else 
		{
			captureScreen(driver, "loginTest");
			
			Assert.assertTrue(false);
			logger.info("Login failed");
		}
		
	}
}



