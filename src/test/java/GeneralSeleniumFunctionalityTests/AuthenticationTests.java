package GeneralSeleniumFunctionalityTests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.*;

import java.time.Duration;

public class AuthenticationTests {
    private WebDriver driver;


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser){
        switch (browser.toUpperCase()){
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver","src/main/java/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            default:
                System.setProperty("webdriver.edge.driver", "src/main/java/resources/msedgedriver.exe");
                driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        driver.quit();
    }

    @Parameters({"authenticationPageURL","username","password","loggedInPageURL","expectedLoginMessage","pageTitle","expectedSecurePageContent"})
    @Test(priority = 1, groups = {"positiveTest"})
    public void successfullLogin(String authenticationPageURL,String username, String password,String loggedInPageURL, String expectedLoginMessage, String pageTitle, String expectedSecurePageContent) {

        driver.get(authenticationPageURL);

        WebElement usernameField = driver.findElement(By.xpath("//input[@id=\"username\"]"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.xpath("//label[@for=\"password\"]//following-sibling::input"));
        passwordField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        submitButton.click();

        String actualMessage = driver.findElement(By.xpath("//div[@id=\"flash\"]")).getText();

        //Verify that we are on the correct URL
        Assert.assertEquals("Unexpected URL address after login!", loggedInPageURL, driver.getCurrentUrl());

        //Verify that the correct login message is displayed.
        Assert.assertTrue(actualMessage.contains(expectedLoginMessage));

        //Verify that we are displaying the correct page title
        Assert.assertEquals("Unexpected page title!", pageTitle, driver.getTitle());

        String actualPageInformation = driver.findElement(By.className("subheader")).getText();

        //Verify that we are displaying the correct page info
        Assert.assertEquals("Incorrect page info displayed", actualPageInformation, expectedSecurePageContent);
    }

    @Parameters({"authenticationPageURL","username","password","loggedInPageURL","expectedLoggedOutMessage"})
    @Test(priority = 1, groups = {"positiveTest"})
    public void successfullLogoutTest(String authenticationPageURL, String username, String password, String loggedinPageURL, String expectedLoggedOutMessage) {

        driver.get(authenticationPageURL);

        WebElement usernameField = driver.findElement(By.xpath("//input[@id=\"username\"]"));
        usernameField.sendKeys(username);


        WebElement passwordField = driver.findElement(By.xpath("//label[@for=\"password\"]//following-sibling::input"));
        passwordField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        submitButton.click();

        //Verify that we are on the correct URL
        Assert.assertEquals("Unexpected URL address after login!", loggedinPageURL, driver.getCurrentUrl());

        driver.findElement(By.linkText("Logout")).click();

        String actualLoggedOutMessage = driver.findElement(By.id("flash")).getText();

        //Verify that the correct logout message is displayed
        Assert.assertTrue(actualLoggedOutMessage.contains(expectedLoggedOutMessage));

    }

    @Parameters({"authenticationPageURL","username","password","expectedErrorMessage"})
    @Test(priority = 2, groups = {"negativeTest"})
    public void invalidLoginTest(String authenticationPageURL,String username, String password, String expectedErrorMessage) {

        driver.get(authenticationPageURL);

        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(username);


        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        submitButton.click();

        //Verify that we are on the correct URL
        Assert.assertEquals("We are not on the correct URL!",driver.getCurrentUrl(),authenticationPageURL);

        String actualInvalidUsernameErrorMessage = driver.findElement(By.id("flash")).getText();
        //Verify that the correct error message is displayed.
        Assert.assertTrue(actualInvalidUsernameErrorMessage.contains(expectedErrorMessage));

    }


}