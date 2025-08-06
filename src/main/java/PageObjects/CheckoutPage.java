package PageObjects;

import AbstractComponent.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponents {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryInput;

    @FindBy(css = ".ta-item span")
    List<WebElement> countryOptions;

    @FindBy(xpath = "//a[.='Place Order ']")
    WebElement placeOrderButton;


    public void selectCountry(String countryName) {
        countryInput.sendKeys(countryName);
        waitForAllElementToAppear(countryOptions);
        WebElement country = countryOptions.stream()
                .filter(option -> option.getText().equalsIgnoreCase(countryName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found: " + countryName));
        country.click();
    }

    public void clickPlaceOrderButton() {
        placeOrderButton.click();
    }

}

