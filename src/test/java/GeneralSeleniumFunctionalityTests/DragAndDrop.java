package GeneralSeleniumFunctionalityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class DragAndDrop {

    private WebDriver driver;


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
    }

    @Parameters({"pageURL"})
    @Test
    public void dragAndDrop(String pageURL) throws InterruptedException {
        driver.get(pageURL);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));


        WebElement itemToDrag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='draggable']")));
        WebElement whereToDrop = driver.findElement(By.xpath("//div[@id='droppable']"));

        Actions action = new Actions(driver);

        action.dragAndDrop(itemToDrag,whereToDrop).build().perform();
        driver.switchTo().defaultContent();

        Thread.sleep(3000);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }


}
