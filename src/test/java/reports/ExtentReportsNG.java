package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {



    public static ExtentReports getReportObject() {

        String filePath = System.getProperty("user.dir") + "\\reports\\index.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "John Doe");
               return extent;






    }


}