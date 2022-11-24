package GeneralSeleniumFunctionalityTests;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PageScrolling {
    private WebDriver driver;
    private org.openqa.selenium.interactions.Actions actions;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUP(String browser){
        switch (browser.toUpperCase()){
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                System.setProperty("webdriver.chrome.driver","src/main/java/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            default:
                System.setProperty("webdriver.edge.driver","src/main/java/resources/msedgedriver.exe");
                driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        actions = new org.openqa.selenium.interactions.Actions(driver);
    }


    @Parameters({"pageURL"})
    @Test(priority = 1)
    public void pageScroll(String pageURL) throws InterruptedException {
        driver.get(pageURL);

        //Casting the driver to JavascriptExecutor. Basically our drier will now be aware that it can execute javascripts
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        List<WebElement> tableRows = driver.findElements(By.xpath("//div[@class='tableFixHead']/table/tbody/tr/td[4]"));

        js.executeScript("document.querySelector('.tableFixHead').scrollTop=500");

        int sum = 0;
        for (WebElement element: tableRows) {
            sum += Integer.parseInt(element.getText());
        }

        int expectedSum = Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());


        Assert.assertEquals(expectedSum,sum);

        Thread.sleep(3000);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
