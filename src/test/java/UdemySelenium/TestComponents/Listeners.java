package UdemySelenium.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import UdemySelenium.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportsNG.getReportObject();
	ExtentTest test;
	
	//if we use parallel in the testng xml, then the test will execute paralelly and the "test" variable which is reference of the extentReports
	//will be overridden while executing the tests parallely, so the failure may show up in different test when that particular test is not failed actually
	// in order to overcome this prob in the HTML Extent Report, we can use ThreadLocal class which helps us to identify the variables uniquely by giving unique ID
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();     //Thread safe--> it will identify the thread uniquely 

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);                  //unique thread ID will be alloted so that override is overcome 
		                                       //Eg: for the "test" variable used in the ErrorValidation method is giving an ID and that will persist for all the execution
		                                       //and will not be overriden with any other "test" variable used in another class/method
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.get().log(Status.PASS, "Test has passed");

		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.get().fail(result.getThrowable()); // generates logs for the failure reason

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName()); // taking ss
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName()); // attaching the taken ss to extent
																						// reports

		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		extent.flush();

		ITestListener.super.onFinish(context);
	}

}
