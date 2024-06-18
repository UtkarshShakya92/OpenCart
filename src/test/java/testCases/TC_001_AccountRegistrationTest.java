package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTestClass;

public class TC_001_AccountRegistrationTest extends BaseTestClass  {
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest  ****");
		logger.debug("This is a debug log message");
		
		try
		{
		HomePage hp =  new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link.. ");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link.. ");
		
		AccountRegistrationPage arp = new AccountRegistrationPage(driver) ;
		
		logger.info("Providing customer details...");
		arp.setFirstName(randomString().toUpperCase());
		arp.setLastName(randomString().toUpperCase());
		arp.setEmail(randomString()+"@gmail.com");
		arp.setTelephone(randomNumber());
		
		//AS password will be same on password and confirm password , we need to take it on varible first and pass to set password 
		
		String password = randomPassword();
		
		arp.setPassword(password);
		arp.setConfirmPassword(password);
		arp.setPolicy();
		arp.setContinue();
		logger.info("Validating expected message..");
		String cnfmsg = arp.getConfirmationMsg();
		
		Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");
		
		logger.info("Test passed");
		}
		
		catch(Exception e)
		{
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		}
		
		finally 
		{
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}
	}
	
	
}
