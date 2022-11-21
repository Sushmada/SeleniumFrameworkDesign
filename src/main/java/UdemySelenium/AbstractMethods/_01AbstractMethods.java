package UdemySelenium.AbstractMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import UdemySelenium.pageObjectModel._03CartPage;
import UdemySelenium.pageObjectModel._06OrdersPage;

public class _01AbstractMethods {

	WebDriver driver;

	public _01AbstractMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="ul li:nth-child(4) button")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement OrdersButton;

	public void waitElementToAppear(By waitForProducts) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(waitForProducts));
	}
	
	public void waitForToast(By byToast)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byToast));
	}

	public void waitElementToDisappear() throws InterruptedException {
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// since there is an internal loading in the app, the wait is so long so we are
		// using thread.sleep
		Thread.sleep(7000);
	}
	
	public void waitWebElementToAppear(WebElement waitForProducts) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(waitForProducts));
	}
	
	public _03CartPage cartButtonClick()
	{
		cartButton.click();
		return new _03CartPage(driver);
		
	}
	
	public _06OrdersPage OrdersButtonClick()
	{
		OrdersButton.click();
		return new _06OrdersPage(driver);
	}
	

	

}
