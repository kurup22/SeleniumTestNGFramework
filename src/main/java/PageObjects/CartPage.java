package PageObjects;

import AbstractComponent.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

WebDriver driver;

     public CartPage(WebDriver driver) {
         super(driver);
         this.driver=driver;
         PageFactory.initElements(driver,this);

     }

     @FindBy(css=".cartSection h3")
     List<WebElement> cartElements;

     @FindBy(xpath="//button[.='Checkout']")
     WebElement checkoutButton;

     public void waitForCartPageToLoad() {
         waitForAllElementToAppear(cartElements);
     }

     public Boolean validateProductName(String productName) {
         return cartElements.stream().anyMatch(ele->
                 ele.getText().equalsIgnoreCase(productName));
     }

     public CheckoutPage clickCheckoutButton() {
         checkoutButton.click();
         CheckoutPage checkoutPage= new CheckoutPage(driver);
         return checkoutPage;
     }

 }
