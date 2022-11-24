package GeneralSeleniumFunctionalityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserNavigation {
    String baseUrl = "http://the-internet.herokuapp.com/";
    String authenticationUrl = "http://the-internet.herokuapp.com/login";

    @Test
    public void navigationTest(){
        System.setProperty("webdriver.chrome.driver","src/main/java/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(baseUrl);

        WebElement forAuthenticationLink = driver.findElement(By.linkText("Form Authentication"));
        forAuthenticationLink.click();

        //Verify that we navigated to authenticationUrl
        Assert.assertEquals(driver.getCurrentUrl(),authenticationUrl);

        //navigate back to base url
        driver.navigate().back();

        //Verify that we are on the base url
        Assert.assertEquals(driver.getCurrentUrl(),baseUrl);

        //navigate back to the authentication page
        driver.navigate().forward();

        //Verify that we are on the authenticationUrl
        Assert.assertEquals(driver.getCurrentUrl(),authenticationUrl);

        driver.quit();
    }
}
