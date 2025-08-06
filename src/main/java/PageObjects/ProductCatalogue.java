package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".card-body")
    List<WebElement> cards;

    @FindBy(css = ".ngx-spinner-overlay")
     WebElement spinner;

    @FindBy(css = "[aria-label='Product Added To Cart']")
    WebElement addedToCartMessage;

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartLink;


    public void waitForAllElementToAppear() {
        waitForAllElementToAppear(cards);
      }

    public void clickAddToCartOfProduct(String productName) {
        WebElement prod=cards.stream()
                .filter(card -> card.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        prod.click();

    }

    public void waitForSpinnerToDisappear() {
        waitForInvisibilityOfElement(spinner);
    }
    public void validateAddedToCartMessage() {
        waitForVisibilityOfElement(addedToCartMessage);
    }
    public void waitForSpinnerToAppear() {
        waitForVisibilityOfElement(spinner);
    }

    public void waitForAddedToCartMessageToDisappear() {
        waitForInvisibilityOfElement(addedToCartMessage);

    }

    public void goToCartPage(){
        cartLink.click();

    }

}
