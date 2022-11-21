package UdemySelenium.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemySelenium.AbstractMethods._01AbstractMethods;

public class _01LoginPage extends _01AbstractMethods{
	
	WebDriver driver;
	
	public _01LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="userEmail")
	WebElement EmailTextfield;
	
	@FindBy(id="userPassword")
	WebElement PasswordTextfield;
	
	@FindBy(id="login")
	WebElement LoginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	//driver.get("https://rahulshettyacademy.com/client");
	
	public _02ProductCatalog Login(String email, String password)
	{
		EmailTextfield.sendKeys(email);
		PasswordTextfield.sendKeys(password);
		LoginButton.click();
		return new _02ProductCatalog(driver);
	}
	
	public void Url()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

	
}
