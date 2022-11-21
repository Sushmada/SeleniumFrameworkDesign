package UdemySelenium.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemySelenium.AbstractMethods._01AbstractMethods;

public class _05ConfirmationPage extends _01AbstractMethods {
	
	WebDriver driver;
	
	public _05ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confrimMsg;
	
	public String orderConfirmation()
	{
		return confrimMsg.getText();
	}

}
