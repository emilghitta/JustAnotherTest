package GeneralSeleniumFunctionalityTests;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class DynamicControlsTest {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUP(@Optional("chrome") String browser){
        switch (browser.toUpperCase()){
            case "CHROME":
                System.setProperty("webdriver.chrome.driver","src/main/java/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.edge.driver","src/main/java/resources/msedgedriver.exe");
                driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Parameters({"pageURL","goneMessage","backMessage"})
    @Test
    public void addAndRemoveTest(String pageURL,String gMessage ,String bMessage){
        driver.get(pageURL);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        //Locate and click on checkbox
        WebElement checkbox = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));
        checkbox.click();

        //Verify if checkbox is selected
        Assert.assertTrue(checkbox.isSelected());

        //Find and click on the remove button
        WebElement removeButton = driver.findElement(By.xpath("//form[@id=\"checkbox-example\"]/button"));
        removeButton.click();

        //Wait until the message is available and then capture it
        WebElement goneMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));

        //Assert that we are displaying the correct message
        Assert.assertEquals(gMessage,goneMessage.getText());

        //Locate and click on the add button
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
        addButton.click();

        //Verify and assert that the checkbox is displayed
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkbox']"))).isDisplayed());

        //Locate and verify that the correct back message is displayed
        WebElement backMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        Assert.assertEquals(bMessage,backMessage.getText());

    }

    @Parameters({"pageURL","textInput","enabledMessage","disabledMessage"})
    @Test
    public void enableAndDisableInputField(String pageURL, String textInput, String enabledMessage, String disabledMessage){
        driver.get(pageURL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Get text field and verify that the default state is disabled
        WebElement textField = driver.findElement(By.xpath("//input[@type='text']"));
        Assert.assertFalse(textField.isEnabled());

        //Get and click the action button
        WebElement enableOrDisableButton = driver.findElement(By.xpath("//form[@id='input-example']/button"));
        enableOrDisableButton.click();

        //Get the enabled message and assert that it's the correct one
        WebElement actionMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='input-example']/p")));
        Assert.assertEquals(enabledMessage,actionMessage.getText());

        //Type inside the input field and verify that we can do that
        textField.sendKeys(textInput);

        //Disable text field
        enableOrDisableButton.click();
        Assert.assertTrue(wait.until(ExpectedConditions.attributeContains(textField, "disabled","")));

        //Verify that the text remains inside the text field
        Assert.assertEquals(textInput,textField.getAttribute("value"));

        //the disabled message is displayed
        WebElement dMessage = driver.findElement(By.xpath("//form[@id='input-example']/p"));
        Assert.assertEquals(disabledMessage,dMessage.getText());


    }
}
