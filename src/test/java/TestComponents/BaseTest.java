package TestComponents;

import PageObjects.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
   public  WebDriver driver;
    public Properties prop;
    public LandingPage landingPage;

    public BaseTest() {
        this.prop = new Properties();
    }

    public Properties propObject() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
        prop.load(fis);
        return prop;

    }

    public WebDriver initilizeDriver() throws IOException {

        prop = propObject();
        String propBrowser = prop.getProperty("browser");
        String sysBrowser = System.getProperty("browser");
        String browser = (sysBrowser != null) ? sysBrowser : propBrowser;

        switch(browser.toLowerCase()) {

            case "chrome":
                ChromeOptions options=new ChromeOptions();
                options.addArguments("--force-device-scale-factor=1.2");
                // Initialize ChromeDriver here
                driver=new ChromeDriver(options);
                break;
            case "firefox":
                driver=new FirefoxDriver();
                // Initialize FirefoxDriver here
                break;
            case "edge":
                driver=new EdgeDriver();
                // Initialize EdgeDriver here
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        // Code to initialize the WebDriver instance
        // This could include setting up browser options, capabilities, etc.
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }


    @BeforeMethod
    public LandingPage launchApplication() throws IOException {

        driver= initilizeDriver();
        landingPage=new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
