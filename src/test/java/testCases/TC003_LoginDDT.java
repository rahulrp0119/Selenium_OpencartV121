package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")//getting data providers from diffrent class
	public void verify_loginDDT(String email, String password, String exp) throws InterruptedException
	{  
		logger.info("**********Starting TC003_LoginDDT*************");
	
		try {
		//Homepage
				HomePage hp= new HomePage(driver);
				hp.clickMyAccount();
				logger.info("clicked on MyAccount link on the home page..");
				hp.clickLogin();
				logger.info("clicked on login link under MyAccount..");
		//Login
				LoginPage lp= new LoginPage(driver);
				logger.info("Entering valid email and password..");
				lp.setEmail(email);
				lp.setPassword(password);
				lp.clickLogin();
				logger.info("clicked on login button..");
		
		//verification of MyAcccountPage
				MyAccountPage mac= new MyAccountPage(driver);
				boolean tgtPg = mac.isMyAccountExists();
				
				/*Data is valid  - login success - test pass  - logout
				Data is valid -- login failed - test fail

				Data is invalid - login success - test fail  - logout
				Data is invalid -- login failed - test pass
				*/
				
				if(exp.equalsIgnoreCase("Valid"))
				{
					if(tgtPg==true)///*Data is valid  - login success - test pass  - logout
					{   
						mac.clickLogout();
						Assert.assertTrue(true);
						
					}
					else {Assert.assertTrue(false);}//Data is valid -- login failed - test fail
					
				}
				if(exp.equalsIgnoreCase("Invalid"))
				{
					if(tgtPg==true)//Data is invalid - login success - test fail  - logout
					{
						mac.clickLogout();
						Assert.assertTrue(false);
			
					}
					else {Assert.assertTrue(true);}//Data is invalid -- login failed - test pass
					
				}
				
		}
		catch(Exception e)
		{
			Assert.fail();
			
		}
		Thread.sleep(3000);		
	}
}
