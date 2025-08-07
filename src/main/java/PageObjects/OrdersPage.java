package PageObjects;

import AbstractComponent.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponents {

    WebDriver  driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> ordersList;

    public void waitForOrdersList() {
        waitForAllElementToAppear(ordersList);
    }

    public String getOrderProductName(String productName) {

        String orderProductName=ordersList.stream().filter(s->s.getText().equals(productName)).findFirst().
                map(s->s.getText()).orElse(null);

        return orderProductName;

    }




}
