package Tests;

import PageObjects.*;
import TestComponents.BaseTest;
import com.beust.jcommander.Parameter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {
   // String productName= "ZARA COAT 3";

   //@Parameters({"productName"})
    @Test(dataProvider = "getData")

    public void submitOrder(HashMap<String,String> input) throws Exception {
        // This is a placeholder for the main method.
        // You can add your test logic here.


        ProductCatalogue productCatalogue = landingPage.login(input.get("username"), input.get("password"));
        productCatalogue.waitForAllElementToAppear();
        productCatalogue.clickAddToCartOfProduct(input.get("product"));

        productCatalogue.waitForSpinnerToAppear();
        productCatalogue.validateAddedToCartMessage();

        productCatalogue.waitForAddedToCartMessageToDisappear();
        productCatalogue.waitForSpinnerToDisappear();

        CartPage cartPage = productCatalogue.goToCartPage();

        cartPage.waitForCartPageToLoad();
        Boolean cartProductCheck = cartPage.validateProductName(input.get("product"));
        Assert.assertTrue(cartProductCheck);

//        JavascriptExecutor js= (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,2000)");

        CheckoutPage checkout = cartPage.clickCheckoutButton();
        //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder='Select Country']"))).sendKeys("IND");
        checkout.selectCountry("United States");

        OrderConfirmationPage orderConfirmationPage = checkout.clickPlaceOrderButton();

        Assert.assertEquals(orderConfirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
        //js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[.='Place Order ']")));
        //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[.='Place Order ']")))).click();

        // String ThankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText();
        //Assert.assertEquals(ThankYouMessage,"THANKYOU FOR THE ORDER.");


    }

    //@Parameters({"productName"})
    @Test(dependsOnMethods = {"submitOrder"},dataProvider = "getData")
    public void validateOrdersPage(HashMap<String,String> input) throws IOException {

        landingPage.login(input.get("username"), input.get("password"));
        OrdersPage ordersPage = landingPage.goToOrdersPage();
        ordersPage.waitForOrdersList();
        Assert.assertEquals(ordersPage.getOrderProductName(input.get("product")), input.get("username"));


    }

    @DataProvider
    public Object[][] getData() throws IOException {

       List<HashMap<String,String>> data= getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\data\\dataProvider.json");
       return new Object[][] {{data.get(0)},{data.get(1)}};



    }

}

