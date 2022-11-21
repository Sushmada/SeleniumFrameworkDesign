package UdemySelenium.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemySelenium.AbstractMethods._01AbstractMethods;

public class _03CartPage extends _01AbstractMethods {
	
	WebDriver driver;
	
	public _03CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='cartSection'] h3")
	List<WebElement> cartProds;
	
	@FindBy(css="ul[style*='list-style-type'] button")
	WebElement checkoutButton;
	
	public boolean prodSelected(String prodName)
	{
		boolean prodMatch = cartProds.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(prodName));
		return prodMatch;
	}
	
	public _04CustomerDetailsPage checkout()
	{
		checkoutButton.click();
		return new _04CustomerDetailsPage(driver);
	}

}
