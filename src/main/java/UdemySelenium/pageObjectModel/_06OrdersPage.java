package UdemySelenium.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemySelenium.AbstractMethods._01AbstractMethods;

public class _06OrdersPage extends _01AbstractMethods {

	WebDriver driver;
	
	public _06OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderedProducts;
	
	public boolean OrderedProducts(String prodName)
	{
		boolean orderMatch = OrderedProducts.stream().anyMatch(orderedProd-> orderedProd.getText().equalsIgnoreCase(prodName));
		return orderMatch;
	}

}
