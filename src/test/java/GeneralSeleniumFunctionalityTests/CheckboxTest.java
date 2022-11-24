package GeneralSeleniumFunctionalityTests;

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

import java.util.List;

public class CheckboxTest {
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

    @Parameters({"pageURL"})
    @Test
    public void checkboxTest(String pageURL){
        driver.get(pageURL);
        WebElement familyAndFriendCheckbox = driver.findElement(By.xpath("//input[@id=\"ctl00_mainContent_chk_friendsandfamily\"]"));

        //Verify if the checkbox is not selected by default
        Assert.assertFalse(familyAndFriendCheckbox.isSelected());


        familyAndFriendCheckbox.click();

        //Verify if the checkbox is selected
        Assert.assertTrue(familyAndFriendCheckbox.isSelected());

        //get all the checkboxes
        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type=\"checkbox\"]"));

        Assert.assertEquals(6, allCheckboxes.size());
    }

    @AfterMethod
    private void tearDown(){
        driver.quit();
    }
}
