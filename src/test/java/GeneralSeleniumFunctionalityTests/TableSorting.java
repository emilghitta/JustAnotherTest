package GeneralSeleniumFunctionalityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TableSorting {
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
    public void tableSort(String pageURL){
        driver.get(pageURL);

        WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='page-menu']"));
        Select select = new Select(dropdownMenu);
        select.selectByValue("20");

        List<WebElement> table = driver.findElements(By.xpath("//tr/td[1]"));

        List<String> originalList =table.stream().map(WebElement::getText).collect(Collectors.toList());

        List<String> expectedSortedList = originalList.stream().sorted().collect(Collectors.toList());

        //First we verify that the table is not sorted by default
        Assert.assertNotEquals(originalList,expectedSortedList);

        //Click and sort the table
        WebElement sortHeader = driver.findElement(By.xpath("//span[contains(text(),'Veg/fruit name')]/.."));
        sortHeader.click();

        table = driver.findElements(By.xpath("//tr/td[1]"));

        List<String> sortedList = table.stream().map(WebElement::getText).collect(Collectors.toList());

        for (String listItem: sortedList) {
            System.out.println(listItem);
        }

        Assert.assertEquals(sortedList,expectedSortedList);

        //Get price of each vegetable

        List<String> price = table.stream().filter(s -> s.getText().contains("Mango")).map(this::getPriceOfVeggie).collect(Collectors.toList());
        price.forEach(System.out::println);
        

    }

    private String getPriceOfVeggie(WebElement veggieName){
        return veggieName.findElement(By.xpath("following-sibling::td[1]")).getText();
    }

    @AfterMethod
    private void tearDown(){
        driver.quit();
    }

}
