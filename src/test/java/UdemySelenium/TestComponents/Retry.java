package UdemySelenium.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {             //IRetryAnalyzer class will have a method called retry will will execute only failed tests again
		                                                   //control will also come to the retry method if any test fails and execute again based on the condition given
		if(count<maxTry)                                   //numnber of times the failed test execute will depend on the condition given inside the method
		{                                                 //this method will always return "false", which means the control will go out of the method
			count++;                                      //so inside the if condition we have written code to execute the max num of times if any test fail and returned true inside
			return true;                                  //if maxtry is 2, the test will rerun 2 times and the result is counted only for the last try and the frst 2 will be treated as skipped
		}                                                 //this is not the part of ItestListener hence shd be written in a seperate clss implementing IRetryAnalyzer
		                                                  //and the method which should be run again shd be explicilty defined in the method
		return false;
	}

}
