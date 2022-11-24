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
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class BrokenLinks {
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

    @Parameters({"pageURL"})
    @Test
    public void brokenLink(String pageURL) throws IOException {
        driver.get(pageURL);
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> footerLinks = driver.findElements(By.xpath("//div[@id='gf-BIG']//a"));

        for (WebElement element: footerLinks) {
            String elementHref = element.getAttribute("href");
            HttpURLConnection connection  = (HttpURLConnection) new URL(elementHref).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();

            softAssert.assertTrue(connection.getResponseCode() != 404);

            System.out.println(connection.getResponseCode());
            connection.disconnect();
        }

        softAssert.assertAll();

    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
