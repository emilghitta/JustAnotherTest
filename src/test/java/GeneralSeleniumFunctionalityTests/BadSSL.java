package GeneralSeleniumFunctionalityTests;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


public class BadSSL {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUP(String browser){
        switch (browser.toUpperCase()){
            case "FIREFOX":
                FirefoxOptions fOptions = new FirefoxOptions();
                Proxy proxy = new Proxy();
                proxy.setHttpProxy("ipaddress:4444");
                fOptions.setCapability("proxy",proxy);
                fOptions.setAcceptInsecureCerts(true);
                System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
                driver = new FirefoxDriver(fOptions);
                break;
            case "CHROME":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setAcceptInsecureCerts(true);
                System.setProperty("webdriver.chrome.driver","src/main/java/resources/chromedriver.exe");
                driver = new ChromeDriver(cOptions);
                break;

            default:
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setAcceptInsecureCerts(true);
                System.setProperty("webdriver.edge.driver","src/main/java/resources/msedgedriver.exe");
                driver = new EdgeDriver(eOptions);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Parameters({"pageURL","username","password"})
    @Test
    public void sessionCookieDeletion(String pageURL,String username, String password) throws IOException {
        driver.get(pageURL);

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        Assert.assertEquals("https://the-internet.herokuapp.com/secure",driver.getCurrentUrl());

        driver.manage().deleteCookieNamed("rack.session");

        driver.navigate().refresh();

        Assert.assertEquals("https://the-internet.herokuapp.com/login",driver.getCurrentUrl());

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
    }


    @Parameters({"pageURL"})
    @Test(priority = 1)
    public void badSSL(String pageURL){
        driver.get(pageURL);
        System.out.println(driver.getTitle());


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
