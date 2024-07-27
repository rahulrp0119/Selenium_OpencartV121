package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("****************** Starting TC002_LoginTest ******************");
		
		try {
		//Homepage
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link on the home page..");
		hp.clickLogin();
		logger.info("clicked on login link under myaccount..");
		
		//Login
		LoginPage lp= new LoginPage(driver);
		logger.info("Entering valid email and password..");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		logger.info("clicked on login button..");
		
		//verifcation of MyAcccountPage
		MyAccountPage mac= new MyAccountPage(driver);
		boolean tgtPg = mac.isMyAccountExists();
	    Assert.assertEquals(tgtPg,true,"Login Failed");
	   // Assert.assertTrue(tgtPg);
		}
		catch (Exception e)
		{
	     Assert.fail();
		}
		logger.info("****************** Finished TC002_LoginTest ******************");
	}
}
