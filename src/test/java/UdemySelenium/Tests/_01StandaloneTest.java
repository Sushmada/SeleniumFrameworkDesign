package UdemySelenium.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class _01StandaloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup(); // to launch chrome driver using setup without using setProperties and
		ChromeDriver driver = new ChromeDriver(); // not having the chromedriver executables in your system
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String prodName = "ADIDAS ORIGINAL";

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("sush@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Bujji!123");
		driver.findElement(By.id("login")).click();
		
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(prodName))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//ng-animating is the class name for loading spinner (if we are unable to find the locator of the spinners we can contact dev)
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Thread.sleep(5000);
		
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul li:nth-child(4) button")));
		
		driver.findElement(By.cssSelector("ul li:nth-child(4) button")).click();
		
	    List<WebElement> cartProds = driver.findElements(By.cssSelector("[class='cartSection'] h3"));
		boolean prodMatch = cartProds.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(prodName));
		Assert.assertTrue(prodMatch);
		
		driver.findElement(By.cssSelector("ul[style*='list-style-type'] button")).click();
		driver.findElement(By.cssSelector(".user__address .text-validated")).sendKeys("india");
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();

	}

}
