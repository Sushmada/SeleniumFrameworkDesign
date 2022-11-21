package UdemySelenium.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemySelenium.AbstractMethods._01AbstractMethods;

public class _04CustomerDetailsPage extends _01AbstractMethods{
	
	WebDriver driver;
	
	public _04CustomerDetailsPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".user__address .text-validated")
	WebElement countryTextfield;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	
	public _05ConfirmationPage customerDetails()
	{
		countryTextfield.sendKeys("india");
		country.click();
		placeOrderButton.click();
		return new _05ConfirmationPage(driver);
	}
	
	
}
