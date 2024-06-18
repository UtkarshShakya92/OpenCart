package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC_002_LoginTest extends BaseTestClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("***********Starting TC_002_Login TEST********");

		try {
			// Home page
			HomePage hp = new HomePage(driver);

			hp.clickMyAccount();
			hp.clickLogin();

			// Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			// My Account page
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetacc = macc.isMyAccountPageExist();
			Assert.assertTrue(targetacc);
		}

		catch (Exception e) {
			Assert.fail();
		}

		logger.info("**** Finished TC_002_LoginTest  ****");
	}
}
