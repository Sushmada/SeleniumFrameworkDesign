package UdemySelenium.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import UdemySelenium.TestComponents.BaseTest;
import UdemySelenium.TestComponents.Retry;
import UdemySelenium.pageObjectModel._01LoginPage;
import UdemySelenium.pageObjectModel._02ProductCatalog;
import UdemySelenium.pageObjectModel._03CartPage;
import UdemySelenium.pageObjectModel._04CustomerDetailsPage;
import UdemySelenium.pageObjectModel._05ConfirmationPage;
import UdemySelenium.pageObjectModel._06OrdersPage;

public class _02OrderPlaceTest extends BaseTest {
	
	String prodName = "ADIDAS ORIGINAL";

	@Test(dataProvider= "getData", groups= {"Purchase"}, retryAnalyzer=Retry.class)
	public void OrderPlace(HashMap<String, String> input) throws IOException, InterruptedException {
		
		_02ProductCatalog productCatalog = loginPage.Login(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalog.productList();
		productCatalog.getProductByName(input.get("product"));
		_03CartPage cartProd = productCatalog.addToCart(input.get("product"));

		boolean prodMatch = cartProd.prodSelected(input.get("product"));
		Assert.assertTrue(prodMatch); // assertions should be in the test
		_04CustomerDetailsPage custDetails = cartProd.checkout();

		_05ConfirmationPage confirm = custDetails.customerDetails(); // NOTE: if Actions are used to location the
																		// country, Actions won't support @FindBy,
		// we shouldnot use @FindBy t=for Actions and shd write the piece of code
		// containig Actions in the method itself

		String confirmMessage = confirm.orderConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.")); // assertions should be in the test
															
	}

	
	@Test(dependsOnMethods= {"OrderPlace"}, dataProvider= "getData", groups= {"Purchase"})
	public void OrderHistory(HashMap<String, String> input)
	{
		_02ProductCatalog productCatalog = loginPage.Login(input.get("email"), input.get("password"));
		_06OrdersPage orderPage = productCatalog.OrdersButtonClick();                        //orderButtonClick() can be called using any reference since it is present in Abstract method
		Assert.assertTrue(orderPage.OrderedProducts(input.get("product")));
	}
	
//	
//	//Getting Exception "cannot inject @Test annotated method"
//	@Test(dependsOnMethods= {"OrderPlace"})
//	public void OrderHistory(String email, String password)
//	{
//		_02ProductCatalog productCatalog = loginPage.Login("sush@gmail.com", "Bujji!123");
//		_06OrdersPage orderPage = productCatalog.OrdersButtonClick();                        //orderButtonClick() can be called using any reference since it is present in Abstract method
//		Assert.assertTrue(orderPage.OrderedProducts(prodName));
//	}
	

	//using hashmap returned from the external json file(PurchaseOrder.json)
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonData(new File(System.getProperty("user.dir")+"//src//test//java//UdemySelenium//data//PurchaseOrder.json"));
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	
	
	//by using Objects
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"sush@gmail.com", "Bujji!123", "ADIDAS ORIGINAL"}, {"bujji77@gmail.com", "Sush!123", "IPHONE 13 PRO"}};
//	}
	
	
	//by using Hash Map (if we have large data to pass then we can use hash map)
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "sush@gmail.com");
//		map.put("password", "Bujji!123");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "bujji77@gmail.com");
//		map1.put("password", "Sush!123");
//		map1.put("product", "IPHONE 13 PRO");
//		
//		return new Object[][] {{map}, {map1}};
//	}
	

}
