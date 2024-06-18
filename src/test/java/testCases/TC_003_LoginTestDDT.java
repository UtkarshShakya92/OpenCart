package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;
import utilities.DataProviders;

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC_003_LoginTestDDT extends BaseTestClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven") // getting data provider from diffrent
																				// class
	public void verify_loginDDT(String email, String password, String exp) {

		logger.info("**** Starting TC_003_LoginDDT *****");

		try {
			// Home page
			HomePage hp = new HomePage(driver);

			hp.clickMyAccount();
			hp.clickLogin();

			// Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			// My Account page
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetacc = macc.isMyAccountPageExist();

			/*
			 * Data is valid - login success - test pass - logout 
			 *                 login failed - test fail
			 * 
			 * Data is invalid - login success - test fail - logout 
			 *                   login failed - test pass
			 */

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetacc == true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				}

				else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetacc == true) {
					macc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} 
		catch (Exception e) 
		{
            Assert.fail();
		}
		
		logger.info("**** Finished TC_003_LoginDDT *****");

	}
}
