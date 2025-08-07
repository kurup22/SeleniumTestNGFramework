package Tests;

import PageObjects.*;
import TestComponents.BaseTest;
import com.beust.jcommander.Parameter;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class SubmitOrder extends BaseTest {

   // String productName= "ZARA COAT 3";


   @Parameters({"productName"})
   @Test

    public void submitOrder(String productName) throws Exception {
        // This is a placeholder for the main method.
        // You can add your test logic here.


        ProductCatalogue productCatalogue=landingPage.login("vavetuts@tits.com","tits@Tuts22");
        productCatalogue.waitForAllElementToAppear();
        productCatalogue.clickAddToCartOfProduct(productName);

        productCatalogue.waitForSpinnerToAppear();
        productCatalogue.validateAddedToCartMessage();

        productCatalogue.waitForAddedToCartMessageToDisappear();
        productCatalogue.waitForSpinnerToDisappear();

        CartPage cartPage=productCatalogue.goToCartPage();

        cartPage.waitForCartPageToLoad();
        Boolean cartProductCheck = cartPage.validateProductName(productName);
        Assert.assertTrue(cartProductCheck);

//        JavascriptExecutor js= (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,2000)");

        CheckoutPage checkout=cartPage.clickCheckoutButton();
      //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder='Select Country']"))).sendKeys("IND");
        checkout.selectCountry("United States");

        OrderConfirmationPage orderConfirmationPage= checkout.clickPlaceOrderButton();

        Assert.assertEquals(orderConfirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
        //js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[.='Place Order ']")));
        //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[.='Place Order ']")))).click();

       // String ThankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText();
        //Assert.assertEquals(ThankYouMessage,"THANKYOU FOR THE ORDER.");

      

    }

    @Parameters({"productName"})
    @Test  (dependsOnMethods = {"submitOrder"})
    public void validateOrdersPage(String productName) throws IOException {

       landingPage.login("vavetuts@tits.com","tits@Tuts22");
       OrdersPage ordersPage=landingPage.goToOrdersPage();
       ordersPage.waitForOrdersList();
       Assert.assertEquals(ordersPage.getOrderProductName(productName),productName);



    }



}
