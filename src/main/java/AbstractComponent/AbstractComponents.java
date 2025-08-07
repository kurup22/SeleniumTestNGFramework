package AbstractComponent;

import PageObjects.CartPage;
import PageObjects.OrdersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponents {

    WebDriver driver;
    WebDriverWait wait;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartLink;

    @FindBy(css="[routerlink*='myorders']")
    WebElement ordersLink;

    public CartPage goToCartPage() {
        cartLink.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;
    }

    public OrdersPage goToOrdersPage() {
        ordersLink.click();
        return new OrdersPage(driver);
    }
    public void waitForAllElementToAppear(List<WebElement> findBy) {

        wait.until(ExpectedConditions.visibilityOfAllElements(findBy));

    }

    public void waitForVisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInvisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }


}
