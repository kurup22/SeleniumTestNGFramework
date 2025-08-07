package PageObjects;

import AbstractComponent.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css = "div[aria-label*='Incorrect']")
    WebElement incorrectLoginMessage;

    @FindBy(css = ".invalid-feedback div")
    WebElement invalidEmailMessage;

    public ProductCatalogue login(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goTo() {

        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getIncorrectLoginMessage() {
        return incorrectLoginMessage.getText();
    }

    public String getInvalidEmailMessage() {
        return invalidEmailMessage.getText();
    }

}
