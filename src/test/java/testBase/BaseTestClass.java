package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.net.URL;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class BaseTestClass {

	
public static WebDriver driver;
public Logger logger;
public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os ,String br) throws IOException
	{
	    logger = LogManager.getLogger(this.getClass());	
	    //loading properties file 
	    FileReader file = new FileReader(".//src//test//resources//config.properties");
	    p= new Properties();
	    p.load(file);
	    
	    
	    if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	    {
	    	DesiredCapabilities Capabilities = new DesiredCapabilities();
	    	
	    	//os
	    	if(os.equalsIgnoreCase("windows"))
	    	{
	    		Capabilities.setPlatform(Platform.WIN10);
	    	}
	    	
	    	else if(os.equalsIgnoreCase("mac"))
	    	{
	    		Capabilities.setPlatform(Platform.MAC);
	    	}
	    	
	    	else
	    	{
	    		System.out.println("No matching os");
				return;
	    	}
	    	
	    	//browser 
	    	
	    	switch(br.toLowerCase())
	    	{
	    	case "chrome": Capabilities.setBrowserName("chrome");break;
	    	case "edge" : Capabilities.setBrowserName("MicrosoftEdge");break;
	    	default : System.out.println("No matching os");return;
	    	}
	    	
	    	driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"),Capabilities);
	    }
	    
	    //os 
	    if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	    {
	    	
	    switch(br.toLowerCase())
	    {
	    case "chrome": driver = new ChromeDriver(); break;
	    case "edge":  driver = new EdgeDriver(); break;
	    case "firefox": driver = new FirefoxDriver(); break;
	    default : System.out.println("No matching browser"); return;
	    }
	    
	    
	    }
	    
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appurl")); //readig url from properties file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomPassword()
	{
		String generatedPassword = RandomStringUtils.randomAlphabetic(6);
		return generatedPassword;
	}
	
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
