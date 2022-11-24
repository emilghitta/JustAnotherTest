package GeneralSeleniumFunctionalityTests;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.Iterator;
import java.util.Set;

public class Actions {

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
    public void hoverTest(String pageURL){
        driver.get(pageURL);

        WebElement figure1 = driver.findElement(By.xpath("//div[@class='figure'][1]"));
        actions.moveToElement(figure1).build().perform();

        Assert.assertTrue(driver.findElement(By.xpath("//h5[contains(text() , 'user1')]/..")).isDisplayed());

        driver.get("https://www.amazon.com/");

        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));

        actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().sendKeys(Keys.DELETE).build().perform();

        actions.moveToElement(searchBox).contextClick().sendKeys(Keys.RETURN).sendKeys(Keys.RETURN).build().perform();
    }

    @Parameters({"windowPageURL"})
    @Test(priority = 0)
    public void windowTest(String windowPageURL) {
        driver.get(windowPageURL);

        WebElement accessLink = driver.findElement(By.xpath("//a[@class='blinkingText']"));
        accessLink.click();

        Set<String> windows= driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentID = it.next();
        String childID = it.next();

        System.out.println(parentID);
        System.out.println(childID);

        driver.switchTo().window(childID);

        String text = driver.findElement(By.cssSelector(".im-para.red")).getText().split("at")[1].trim().split("with")[0].trim();
        driver.close();
        driver.switchTo().window(parentID);

        WebElement username = driver.findElement(By.xpath("//input[@id=\"username\"]"));
        username.sendKeys(text);



    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
