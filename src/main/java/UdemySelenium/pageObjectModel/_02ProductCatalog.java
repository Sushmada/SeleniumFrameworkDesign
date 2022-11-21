package UdemySelenium.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemySelenium.AbstractMethods._01AbstractMethods;

public class _02ProductCatalog extends _01AbstractMethods{

	WebDriver driver;
	
	public _02ProductCatalog(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".card-body button:last-of-type")
	WebElement addButton;
	
	By waitForProducts = By.cssSelector(".mb-3");
	By addProduct = By.cssSelector(".card-body button:last-of-type");
	By byToast = By.cssSelector("#toast-container");
	
	public List<WebElement> productList()
	{
		waitElementToAppear(waitForProducts);
		return products;
		
	}
	
	public WebElement getProductByName(String prodName)
	{
		WebElement prod = productList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(prodName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public _03CartPage addToCart(String prodName) throws InterruptedException
	{
		WebElement prod = getProductByName(prodName);
		prod.findElement(addProduct).click();
		waitForToast(byToast);
		waitElementToDisappear();
		return cartButtonClick();
		
	}
	

	
	

}