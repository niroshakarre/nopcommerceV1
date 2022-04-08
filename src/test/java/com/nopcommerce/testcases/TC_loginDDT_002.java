package com.nopcommerce.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageobject.LoginPage;
import com.nopcommerce.utilities.XLUtils;

public class TC_loginDDT_002 extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void loginTest(String user, String pwd) throws InterruptedException, IOException
	{
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(user);
		logger.info("User Provided");
		
		lp.setPassword(pwd);
		logger.info("Password Provided");
		
		lp.clicklogin();
		logger.info("Login clicked");

		if (driver.getTitle().equals("Dashboard / nopCommerce administration")) 
		{
			Thread.sleep(2000);
			
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
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\nopcommerce\\testdata\\LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]=new String[rownum][colcount];
		for(int i=1;i<=rownum;i++)
		{
			for(int j=1;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
				
		return logindata;
		
	}
	


}
