package GeneralSeleniumFunctionalityTests;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalendarTest {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Parameters({"pageURL","expectedDepartureDate"})
    @Test
    public void calendarTest(String pageURL, String expectedDepartureDate){
        driver.get(pageURL);

        //Finding the calendar button and clicking it to launch it
        WebElement calendarUI = driver.findElement(By.id("ctl00_mainContent_view_date1"));
        calendarUI.click();

        //Finding the next button and clicking it 3 times

        for(int i = 0; i <= 3; i++){
            WebElement calendarUIRB = driver.findElement(By.xpath("//a[@title='Next']"));
            calendarUIRB.click();
        }

        //Click on the 4 date
        WebElement date = driver.findElement(By.xpath("//td[@data-month='1']/a[text()=4]"));
        date.click();

        // Get the departure date and assert
        WebElement departureDate = driver.findElement(By.xpath("//span[@id='view_fulldate_id_1']"));
        Assert.assertEquals(expectedDepartureDate, departureDate.getText());




    }

    @AfterMethod
    private void tearDown(){
        driver.quit();
    }

}
