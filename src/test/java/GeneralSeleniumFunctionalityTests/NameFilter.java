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
import java.util.stream.Collectors;

public class NameFilter {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUP(String browser){
        switch (browser.toUpperCase()){
            case "FIREFOX":
                System.setProperty("webdriver.geck.driver","src/main/java/resources/geckodriver.exe");
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
    public void nameFilterTest(String pageURL){
        driver.get(pageURL);
        String searchString = "R";

        WebElement searchField = driver.findElement(By.xpath("//input[@id='search-field']"));
        searchField.sendKeys(searchString);

        WebElement nextButton;

        List<String> fruits;

        do {
            nextButton = driver.findElement(By.xpath("//a[@aria-label='Next']"));
            List<WebElement> tableFruitNames = driver.findElements(By.xpath("//tr/td[1]"));
            fruits = tableFruitNames.stream().map(WebElement::getText).collect(Collectors.toList());

            if(nextButton.isEnabled()){
                nextButton.click();
            }
        }while(nextButton.getAttribute("aria-disabled").contains("false"));

        String invalidFruit ="";
        boolean failure =false;
        for (String fruit: fruits) {
            if(!fruit.toLowerCase().contains(searchString.toLowerCase())){
                System.out.println("We've hit a failure");
                failure = true;
                invalidFruit = fruit;
                break;
            }
        }
        Assert.assertFalse("The invalid fruit name found is: " +invalidFruit ,failure);
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        driver.quit();
    }

}
