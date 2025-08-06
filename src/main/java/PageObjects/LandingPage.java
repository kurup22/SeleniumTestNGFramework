package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }


    WebElement userEmail=driver.findElement(By.id("userEmail"));
    WebElement userPassword=driver.findElement(By.id("userPassword"));
    WebElement loginButton=driver.findElement(By.id("login"));
}
