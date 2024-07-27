package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	void verify_account_registration()
	{
		
		logger.info("****************** Starting TC001_AccountRegistrationTest ******************");//to log info msg: log.info
		try
		{
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("            Clicked on MyAccount            ");
		hp.clickRegister();
		logger.info("            Clicked on Register            ");

		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		logger.info("            Providing customer details            ");
		//regpage.setFirstName("John2");
		regpage.setFirstName(randomString().toUpperCase());
		//regpage.setLastName("David23");
		regpage.setLastName(randomString().toUpperCase());
		//regpage.setEmail("JohnDavid@gmail.com");
		regpage.setEmail(randomString()+"@gmail.com");
		//regpage.setTelephone("98498416519");
		regpage.setTelephone(randomNum());
		//String password= randomAlphanumeric();

		//regpage.setPassword("xyz123456");
		String pwd = randomAlphaNum();
		regpage.setPassword(pwd);
		
		//regpage.setConfirmPassword("xyz123456");
		regpage.setConfirmPassword(pwd);

		regpage.setPrivacyPolicy();
		regpage.clickContinue();

		logger.info("            Validating expected message            ");
		String cnfmsg = regpage.getConfirmationMsg();
		if(cnfmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed.");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}//detailed report : debug
		
		logger.info("            Finished TC001_AccountRegistrationTest            ");
	}
	
}
