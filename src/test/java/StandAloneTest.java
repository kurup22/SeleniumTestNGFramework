import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
        // This is a placeholder for the main method.
        // You can add your test logic here.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--force-device-scale-factor=1.2");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        String productName = "IPHONE 13 PRO";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.login("vavetuts@tits.com","tits@Tuts22");

        ProductCatalogue productCatalogue=new ProductCatalogue(WebDriver driver);
        productCatalogue.waitForAllElementToAppear();
        productCatalogue.clickAddToCartOfProduct(productName);

        productCatalogue.waitForSpinnerToAppear();
        productCatalogue.validateAddedToCartMessage();
        productCatalogue.waitForSpinnerToDisappear();
        productCatalogue.waitForAddedToCartMessageToDisappear();

        productCatalogue.goToCartPage();
        

        List<WebElement> cartElements = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean cartProductCheck = cartElements.stream()
                .anyMatch(cartElement -> cartElement.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(cartProductCheck);

        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)");

     driver.findElement(By.xpath("//button[.='Checkout']")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder='Select Country']"))).sendKeys("IND");

        List<WebElement> countryOptions = driver.findElements(By.cssSelector(".ta-item span"));

        WebElement country= countryOptions.stream()
                .filter(option -> option.getText().trim().equalsIgnoreCase("India"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found"));


        country.click();

        //js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[.='Place Order ']")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[.='Place Order ']")))).click();

        String ThankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText();
        Assert.assertEquals(ThankYouMessage,"THANKYOU FOR THE ORDER.");


       driver.quit();


    }

}
