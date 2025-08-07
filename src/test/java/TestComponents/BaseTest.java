package TestComponents;

import PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

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

                //options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());
                options.addArguments("--force-device-scale-factor=0.9");
                if(prop.getProperty("headless").equalsIgnoreCase("true") || System.getProperty("headless").equalsIgnoreCase("true")) {
                    options.addArguments("--headless=new");
                }

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


    @BeforeMethod(alwaysRun=true)
    public LandingPage launchApplication() throws IOException {

        driver= initilizeDriver();
        landingPage=new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


       public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {

            String jsonData = FileUtils.readFileToString(new File(filePath), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();

            List<HashMap<String,String>> data=mapper.readValue(jsonData,
                    new TypeReference<List<HashMap<String,String>>>(){});

            return data;


            // Implement logic to read JSON data from the specified file path
            // This could involve using a library like Jackson or Gson to parse the JSON file
        }

        public String getScreenshotPath(String testCaseName,WebDriver driver) throws IOException {

        TakesScreenshot ts=(TakesScreenshot) driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        String destFilePath=System.getProperty("user.dir")+"//reports//screenshots//"+testCaseName+".png";
        File dest=new File(destFilePath);

        FileUtils.copyFile(src, dest);
        return "screenshots/"+testCaseName+".png";

        }

}

