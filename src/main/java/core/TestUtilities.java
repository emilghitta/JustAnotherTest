package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class TestUtilities extends BaseTest{
    private static ObjectMapper mapper = new ObjectMapper();

    public static List<HashMap<String,String>> getJsonDataToMap() throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "/src/main/java/testdata/Data.json"), StandardCharsets.UTF_8);

        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
    }

    public static HashMap<String,String> getsimpleJSON() throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") +"/src/main/java/testdata/SimpleData.json"), StandardCharsets.UTF_8);

        return  mapper.readValue(jsonContent, new TypeReference<HashMap<String, String>>() {
        });
    }

    public  String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/src/test/resources/Reports/screenshots" + testCaseName + ".png"));
        return System.getProperty("user.dir") + "\\src\\test\\resources\\Reports\\screenshots" + testCaseName + ".png";
    }

    public static ExtentReports extentReportsSetup(){
        String reportPath = System.getProperty("user.dir") + "/src/test/resources/Reports";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setReportName("Web automation training test results");
        sparkReporter.config().setDocumentTitle("Training Project test results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }

    public LogEntries generateBrowserLogs(){
        return  driver.manage().logs().get(LogType.BROWSER);
    }
}
