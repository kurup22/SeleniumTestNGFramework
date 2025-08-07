package Tests;

import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WrongLogin extends BaseTest {

    @Test(groups = {"smoke"})
    public void wrongPassword() throws Exception {
        landingPage.login("vavetuts@tits.com", "Asasasa@123");
        String message = landingPage.getIncorrectLoginMessage();
        Assert.assertEquals(message, "Incorrect email or password.");


    }

    @Test
    public void wrongEmail() throws Exception {
        landingPage.login("asasas", "tits@Tutass22");
        String message = landingPage.getInvalidEmailMessage();
        Assert.assertEquals(message, "*Enter Valid Email");


    }
}