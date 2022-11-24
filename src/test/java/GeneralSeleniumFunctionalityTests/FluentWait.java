package GeneralSeleniumFunctionalityTests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class FluentWait {

    private WebDriver driver;
    private Wait<WebDriver> wait;

    @Parameters({"browser"})
    @BeforeMethod
    private void setUP(String browser){
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
        wait = new org.openqa.selenium.support.ui.FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                ;
    }

    @Parameters({"pageURL"})
    @Test
    public void fluentWaitTest(String pageURL){
        driver.get(pageURL);

        WebElement button = driver.findElement(By.xpath("//button"));
        button.click();

        WebElement result = wait.until(driver -> {
            if(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed()){
                return driver.findElement(By.xpath("//div[@id='finish']/h4"));
            }else{
                return  null;
            }

        });

        Assert.assertTrue(result.isDisplayed());

    }



    @AfterMethod
    private void tearDown(){
        driver.quit();
    }

}
