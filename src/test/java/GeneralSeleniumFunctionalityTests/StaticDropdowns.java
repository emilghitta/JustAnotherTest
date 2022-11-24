package GeneralSeleniumFunctionalityTests;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class StaticDropdowns {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Parameters({"pageURL","selectedOption1","selectedOption2","selectedOption3"})
    @Test
    public void staticSelect(String pageURL, String selectedOption1, String selectedOption2, String selectedOption3){
        driver.get(pageURL);

        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select selectDropdown = new Select(staticDropdown);

        selectDropdown.selectByIndex(3);

        Assert.assertEquals(selectDropdown.getFirstSelectedOption().getText(),selectedOption3, "Incorrect dropdown selection");

        selectDropdown.selectByValue(selectedOption2);

        Assert.assertEquals(selectDropdown.getFirstSelectedOption().getText(),selectedOption2, "Incorrect dropdown selection");

        selectDropdown.selectByVisibleText(selectedOption1);

        Assert.assertEquals(selectDropdown.getFirstSelectedOption().getText(),selectedOption1, "Incorrect dropdown slection");

    }

    @Parameters({"pageURL","defaultValue","valueWithAdultIncrement","valueWithChildIncrement","valueWithInfantIncrement"})
    @Test
    public void dropdownWithButtons(String pageURL,String defaultValue, String valueWithAdultIncrement, String valueWithChildIncrement ,String valueWithInfantIncrement){
        driver.get(pageURL);
        WebElement passengersDropdown = driver.findElement(By.id("divpaxinfo"));

        //Assert default value
        Assert.assertEquals(passengersDropdown.getText(),defaultValue,"Default value is invalid");

        //Selecting a different value
        passengersDropdown.click();

        WebElement incrementAdultButton = driver.findElement(By.id("hrefIncAdt"));
        WebElement decrementAdultButton = driver.findElement(By.id("hrefDecAdt"));

        WebElement incrementChildButton = driver.findElement(By.id("hrefIncChd"));
        WebElement decrementChildButton = driver.findElement(By.id("hrefDecChd"));

        WebElement incrementInfantButton = driver.findElement(By.id("hrefIncInf"));
        WebElement decrementInfantButton = driver.findElement(By.id("hrefDecInf"));

        //Increment Adult and assert
        incrementAdultButton.click();
        Assert.assertEquals(passengersDropdown.getText(),valueWithAdultIncrement,"Incremented adult value not valid");

        //Increment child and assert
        incrementChildButton.click();
        Assert.assertEquals(passengersDropdown.getText(),valueWithChildIncrement, "Incremented child value not valid");

        //IncrementInfant and assert
        incrementInfantButton.click();
        Assert.assertEquals(passengersDropdown.getText(),valueWithInfantIncrement,"Incrmeneted infant value not valid");

        //Decrement infant and assert
        decrementInfantButton.click();
        Assert.assertEquals(passengersDropdown.getText(),valueWithChildIncrement, "Drecremented infant value not valid");

        //Decrement child and assert
        decrementChildButton.click();
        Assert.assertEquals(passengersDropdown.getText(),valueWithAdultIncrement, "Decremented child valude not valid");

        //Decrement adult and assert
        decrementAdultButton.click();
        Assert.assertEquals(passengersDropdown.getText(),defaultValue,"Default value is invalid");

    }



    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        driver.quit();
    }
}
