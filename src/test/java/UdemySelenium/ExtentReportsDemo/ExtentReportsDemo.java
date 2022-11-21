package UdemySelenium.ExtentReportsDemo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsDemo {
	
	ExtentReports extent;
	
	@BeforeTest
	public void config()
	{
		// Extent Reports, ExtentSparkReporter
		//create an object fpr ExtentSparkReporter class which accepts path of the report as an argument path should be in string
		String path = System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//creating object for extentReports and integrating both extent reports and extentsparkreporter
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "SushmA");
	}
	
	@Test
	public void InitialDemo()
	{
		ExtentTest test = extent.createTest("Initial Demo");         //creating test in extent reports and the extentReports starts monitoring the tests
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://courses.rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		driver.close();
		
		test.fail("results not matching");                    //failing test explicitly only to reflect in the extent reports results
		extent.flush();                                    //on giving flush, the extent reports stops monitoring the test
	}

}
