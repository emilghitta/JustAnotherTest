package GeneralSeleniumFunctionalityTests;

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

public class Pagination {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
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
    public void paginationTest(String pageURL){
        driver.get(pageURL);


        List<String> fruit;

        int pageNumber = 1;
        do {
            List<WebElement> tableFruitNames = driver.findElements(By.xpath("//tr/td[1]")); //Avoid stale element we must find it again
             fruit = tableFruitNames.stream().map(WebElement::getText).filter(s -> s.contains("Almond")).collect(Collectors.toList());

            if(fruit.size() < 1){
                driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
                pageNumber++;
            }
        }while (fruit.size() <1);

        System.out.println(pageNumber);

    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        driver.quit();
    }


}
