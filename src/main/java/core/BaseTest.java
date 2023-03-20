package core;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.Pages;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    private BrowserDriverFactory browserDriverFactory= new BrowserDriverFactory();
    protected Pages pages;


    @Parameters({"pageURL"})
    @BeforeMethod(alwaysRun = true)
    public void setUP(String pageURL, ITestContext context) throws IOException {

        driver = browserDriverFactory.createDriver();
        context.setAttribute("WebDriver",driver);
        pages = new Pages(this.driver);

        driver.get(pageURL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
