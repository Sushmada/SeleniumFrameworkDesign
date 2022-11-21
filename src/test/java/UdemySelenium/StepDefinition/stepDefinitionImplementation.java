package UdemySelenium.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import UdemySelenium.TestComponents.BaseTest;
import UdemySelenium.pageObjectModel._02ProductCatalog;
import UdemySelenium.pageObjectModel._03CartPage;
import UdemySelenium.pageObjectModel._04CustomerDetailsPage;
import UdemySelenium.pageObjectModel._05ConfirmationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImplementation extends BaseTest {
	
	public _02ProductCatalog productCatalog;
	public _04CustomerDetailsPage custDetails;
	public _05ConfirmationPage confirm;
	public _03CartPage cartProd;
	
	@Given("I landed on the Ecommerce page")
	public void i_landed_on_the_ecommerce_page() throws IOException
	{
		launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String name, String password)
	{
		productCatalog = loginPage.Login(name, password);
	}
	
	 @When("^I add product (.+) to Cart$")
	 public void i_added_product_to_cart(String productName) throws InterruptedException
	 {
		 List<WebElement> products = productCatalog.productList();
		 productCatalog.getProductByName(productName);		
		 cartProd = productCatalog.addToCart(productName);

	 }
	 
	 @And("^Checkout (.+) and submit the order$")
	 public void checkout_and_submit_order(String productName) throws InterruptedException
	 {
		 boolean prodMatch = cartProd.prodSelected(productName);
			Assert.assertTrue(prodMatch); // assertions should be in the test
			custDetails = cartProd.checkout();
			confirm = custDetails.customerDetails(); 
	 }
	 
	 @Then("{string} message is displayed on the Confirmationpage")
	 public void confirmation_message_is_displayed_on_confirmationpage(String string)
	 {
		 String confirmMessage = confirm.orderConfirmation();
         Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
         driver.close();
	 }
	 
	 @Then("{string} Error message is displayed")
	 public void error_message_is_displayed(String error)
	 {
		 Assert.assertEquals(error, loginPage.getErrorMessage());
		 driver.close();
		 
	 }
	
	 @Then("I should not find the {string} in cart")
	 public void i_should_not_find_different_product(String product) throws InterruptedException
	 {
		 boolean prodMatch = cartProd.prodSelected(product);
		 Assert.assertFalse(prodMatch);
		 driver.close();
	 }

}
