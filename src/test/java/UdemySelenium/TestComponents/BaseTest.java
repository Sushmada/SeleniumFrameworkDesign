package UdemySelenium.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import UdemySelenium.pageObjectModel._01LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public _01LoginPage loginPage;
	
	//properties class
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//UdemySelenium//resources//GlobalData.properties");
	    prop.load(fis);
	    //String browserName = prop.getProperty("browser");
	    
	    //sending browser name at the runtime through maven command
	    String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	    
	    if(browserName.contains("chrome"))                //given contains chrome since we have headless mode as well
	    {
	    	ChromeOptions options = new ChromeOptions();             //using ChromeOPtions to run the test in headless mode and passing the "options" into the ChromeDriver
	    	if(browserName.contains("headless"))
	    	{
	    	options.addArguments("headless");             //this option will set to the chrome only if the browser name contains headless
	    	}
	    	WebDriverManager.chromedriver().setup(); // to launch chrome driver using setup without using setProperties and
	    	driver = new ChromeDriver(options); // not having the chromedriver executables in your system
	        driver.manage().window().setSize(new Dimension(1440,900));      //default dimension to run the test in full screen so that every element is visible in selenium
	    }                                                                   //while runing in tghe headless mode
	    
	    else if(browserName.equalsIgnoreCase("firefox"))
	    {
	    	//firefox
	    	System.setProperty("webdriver.gecko.driver", "C:\\Users\\W10-Lenovo\\OneDrive\\Desktop\\Udemy_Selenium\\1. driver executables\\geckodriver.exe");
	    	driver = new FirefoxDriver();
	    }
	    
	    else if(browserName.equalsIgnoreCase("edge"))
	    {
	    	//edge
	    	System.setProperty("webdriver.edge.driver", "edge.exe(eg)");
	    	EdgeDriver driver = new EdgeDriver();
	    }
	   
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver ;
		

	}
	
	@BeforeMethod(alwaysRun=true)
	public _01LoginPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		loginPage = new _01LoginPage(driver);
		loginPage.Url();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonData(File filePath) throws IOException
	{
		//to convert Json to String
		String jsontext = FileUtils.readFileToString(filePath, StandardCharsets.UTF_8);
		
		//converting String to Hashmap  ---> import Jackson DataBind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsontext, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	//Screenshot utility(script)
		public String getScreenshot(String testcaseName) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir")+"//Reports"+testcaseName+".png");
			FileUtils.copyFile(source, dest);
			return System.getProperty("user.dir")+"//Reports"+testcaseName+".png";
		}
	

}
