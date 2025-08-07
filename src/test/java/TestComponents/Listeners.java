package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentReportsNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReportsNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Store the test in ThreadLocal for use in other methods
        // ThreadLocal <ExtentTest> test2 = new ThreadLocal<>();


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test passes
        test.log(Status.PASS, "TestCase Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Code to execute when a test fails
        extentTest.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        String filePath = null;
        try {
            filePath = getScreenshotPath(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Code to execute when a test is skipped
        System.out.println("Test skipped: " + result.getName());
    }

    // Other methods can be overridden as needed
    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // ✅ MUST CALL THIS!
    }
}