package pageObjects;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	


	@FindBy(id="input-firstname")
	WebElement txtFirstname;
	
	@FindBy(id ="input-lastname")
	WebElement txtLasttname;
	
	@FindBy(id="input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-telephone")
	WebElement txtTelephone;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(id="input-confirm")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@type ='checkbox']")
	WebElement chkdPolicy;
	
	@FindBy(xpath ="//input[@type ='submit']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[.='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	//action methods 
	
	
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLasttname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String phone)
	{
		txtTelephone.sendKeys(phone);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setPolicy()
	{
		chkdPolicy.click();
	}
	
	public void setContinue()
	{
		btnContinue.click();
		
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnContinue);*/
	}
	
	public String getConfirmationMsg()
	{
		try {
		return (msgConfirmation.getText());
		}
		
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
}
