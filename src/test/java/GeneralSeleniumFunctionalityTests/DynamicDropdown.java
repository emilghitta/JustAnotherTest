package GeneralSeleniumFunctionalityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicDropdown {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod
    private void setUp(String browser){
        switch (browser.toUpperCase()){
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                System.setProperty("webdriver.edge.driver","src/main/java/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver","src/main/java/resources/chromedriver.exe");
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
    }

    @Parameters({"pageURL","defaultFromValue","defaultToValue","expectedNewFromValue","expectedNewToValue"})
    @Test
    public void dynamicDropdownTest(String pageURL, String defaultFromValue, String defaultToValue, String expectedNewFromValue, String expectedNewToValue){
        driver.get(pageURL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Locate from and to fields
        WebElement fromInputField = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
        WebElement toInputField = driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));

        //Assert default values
        Assert.assertEquals(fromInputField.getAttribute("value"),defaultFromValue,"Invalid default from value");
        Assert.assertEquals(toInputField.getAttribute("value"),defaultToValue, "Invalid default to value");

        //Select from field and assert
        fromInputField.click();
        WebElement chennaiOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@value=\"MAA\"]")));
        chennaiOption.click();

        Assert.assertEquals(fromInputField.getAttribute("value"),expectedNewFromValue,"Invalid new from value");

        //Select to field and assert
        toInputField.click();
        WebElement adampurOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@value='AIP'])[2]")));
        adampurOption.click();

        Assert.assertEquals(toInputField.getAttribute("value"),expectedNewToValue,"Invalid new to value");

    }

    @AfterMethod
    private void tearDown(){
        driver.quit();
    }
}
