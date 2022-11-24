package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listeners extends TestUtilities implements ITestListener{
    ExtentTest test;
    ExtentReports extentReports = TestUtilities.extentReportsSetup();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //unique Thread ID
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,"Test passed successfully");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL,"Test failed!");
        extentTest.get().fail(result.getThrowable());
        String filePath = getScreenshot(result.getMethod().getMethodName(),(WebDriver) result.getTestContext().getAttribute("WebDriver"));
        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP,"Test skipped!");

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

}
