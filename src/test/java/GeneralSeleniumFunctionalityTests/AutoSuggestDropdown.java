package GeneralSeleniumFunctionalityTests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AutoSuggestDropdown {
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

    @Parameters({"pageURL","searchString","desiredSearchString"})
    @Test
    public void autoSuggestDropdown(String pageURL, String searchString, String desiredSearchString) {
        driver.get(pageURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement inputField = driver.findElement(By.xpath("//input[@id=\"autosuggest\"]"));
        inputField.sendKeys(searchString); //ro

        List<WebElement> searchResults = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id=\"ui-id-1\"]/li/a")));


        //Click on the desired search result "Romania"

        for (WebElement element: searchResults) {
            if(element.getText().equals(desiredSearchString)){
                System.out.println("our element");
                element.click();
                break;
            }
        }


        //Assert that the input value is the chosen one
        Assert.assertEquals(desiredSearchString,inputField.getAttribute("value"));

    }

    @AfterMethod
    private void tearDown(){
        driver.quit();
    }

}
