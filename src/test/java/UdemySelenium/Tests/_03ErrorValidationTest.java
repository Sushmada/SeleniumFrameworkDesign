package UdemySelenium.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import UdemySelenium.TestComponents.BaseTest;
import UdemySelenium.TestComponents.Retry;
import UdemySelenium.pageObjectModel._01LoginPage;
import UdemySelenium.pageObjectModel._02ProductCatalog;
import UdemySelenium.pageObjectModel._03CartPage;
import UdemySelenium.pageObjectModel._04CustomerDetailsPage;
import UdemySelenium.pageObjectModel._05ConfirmationPage;

public class _03ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)        //expilcilty given the retryAnalyzer to the method on which we have doubt that it might fail 
	public void LoginErrorValidation() throws IOException {            //due to some reasons and might pass on rerun. So that it will rerun max times given in the retry method  
                                                                       //and the last result is counted, and remaining executions will be treated as skipped
		String prodName = "ADIDAS ORIGINAL";
		_02ProductCatalog productCatalog = loginPage.Login("sush@gmail.com", "Bujji!1234");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());

	}

	@Test(retryAnalyzer=Retry.class)
	public void ProductErrorValidation() throws InterruptedException {
		
		String prodName = "ADIDAS ORIGINAL";

		_02ProductCatalog productCatalog = loginPage.Login("bujji77@gmail.com", "Sush!123");

		List<WebElement> products = productCatalog.productList();
		productCatalog.getProductByName(prodName);
		_03CartPage cartProd = productCatalog.addToCart(prodName);

		boolean prodMatch = cartProd.prodSelected("Zara coat");
		Assert.assertFalse(prodMatch);
	}

}
