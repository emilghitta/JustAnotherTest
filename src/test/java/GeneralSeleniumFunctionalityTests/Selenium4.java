package GeneralSeleniumFunctionalityTests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Selenium4 {
    private WebDriver driver;


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUP(String browser){
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
        driver.manage().deleteAllCookies();
    }

    @Parameters({"pageURL"})
    @Test
    public void relativeLocators(String pageURL) {
        driver.get(pageURL);

        //Above element example

        WebElement inputField = driver.findElement(By.xpath("//div[@class='form-group']/input[@name='name']"));

        WebElement nameLabel = driver.findElement(with(By.tagName("label")).above(inputField));

        System.out.println(nameLabel.getText());

        WebElement calendar = driver.findElement(with(By.tagName("input")).below(driver.findElement(By.xpath("//label[@for='dateofBirth']"))));
        calendar.click();

    }

    @Parameters({"pageURL","secondPageURL"})
    @Test
    public void switchWindow(String pageURL, String secondPageURL){
        driver.get(pageURL);

        String parentHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(secondPageURL);

        WebElement text = driver.findElement(By.xpath("//div[@class='price-title']/h2/span"));
        String test = text.getText();

        driver.close();

        driver.switchTo().window(parentHandle);

        WebElement nameInputField = driver.findElement(By.xpath("//div[@class='form-group']/input[@name='name']"));
        nameInputField.sendKeys(test);
    }

    @Parameters({"pageURL"})
    @Test
    public void partialScreenshot(String pageURL) throws IOException {
        driver.get(pageURL);

        WebElement nameInputField = driver.findElement(By.xpath("//div[@class='form-group']/input[@name='name']"));
        nameInputField.sendKeys("javaTraining");

        File file = nameInputField.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("D:\\Personal\\Training\\training2\\Selenium\\TestAutoApplication\\src\\test\\resources\\screenshots\\logo.png"));

    }

    @Parameters({"pageURL"})
    @Test
    public void getHeightAndWidth(String pageURL){
        driver.get(pageURL);

        WebElement nameInputField = driver.findElement(By.xpath("//div[@class='form-group']/input[@name='name']"));
        System.out.println(nameInputField.getRect().getDimension().getHeight());
        System.out.println(nameInputField.getRect().getDimension().getWidth());

    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        driver.quit();
    }
}
