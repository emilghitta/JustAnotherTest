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

public class JavaAlerts {
    private WebDriver driver;

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
    }

    @Parameters({"pageURL","inputName", "expectedAlertMessage","confirmAlertMessage"})
    @Test
    public void simpleAlert(String pageURL, String inputName, String expectedAlertMessage, String confirmAlertMessage){
        driver.get(pageURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement nameInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='enter-name']")));
        nameInputField.sendKeys(inputName);
        WebElement alertButton = driver.findElement(By.xpath("//input[@id='alertbtn']"));
        alertButton.click();

        Assert.assertEquals(driver.switchTo().alert().getText(),expectedAlertMessage,"Incorrect alert message");
        driver.switchTo().alert().accept();

        nameInputField.sendKeys(inputName);
        WebElement confirmAlertButton = driver.findElement(By.xpath("//input[@id='confirmbtn']"));
        confirmAlertButton.click();

        Assert.assertEquals(driver.switchTo().alert().getText(),confirmAlertMessage,"Incorrect confirm alert message");
        driver.switchTo().alert().accept();

    }

    @AfterMethod
    private void tearDown(){
        driver.quit();
    }
}
