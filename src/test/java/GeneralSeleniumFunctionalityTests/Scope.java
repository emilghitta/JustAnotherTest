package GeneralSeleniumFunctionalityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Scope {
    private WebDriver driver;

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
    }

    @Parameters({"pageURL"})
    @Test
    public void gettAllLinksFromThePage(String pageURL){
        Actions act = new Actions(driver);
        driver.get(pageURL);

        //Get links from entire page
        List<WebElement> allAnchors = driver.findElements(By.tagName("a"));

        Assert.assertEquals(allAnchors.size(),27);

        //Get links from footer only
        WebElement footerSection = driver.findElement(By.xpath("//div[@id='gf-BIG']"));

        List<WebElement> allFooterAnchors = footerSection.findElements(By.tagName("a"));

        Assert.assertEquals(allFooterAnchors.size(),20);

        //Get links from first collumn only
        WebElement firstCollumn = driver.findElement(By.xpath("//a[contains(text(),'Discount Coupons')]/../../.."));

        List<WebElement> allFirstCollumnFooterLinks = firstCollumn.findElements(By.tagName("a"));

        Assert.assertEquals(allFirstCollumnFooterLinks.size(),5);

        //Open each anchor in different tabs

        for (WebElement link: allFirstCollumnFooterLinks) {
            act.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();
        }

        //Close the newly open tabs & get page titles of each tab

        Set<String> windows = driver.getWindowHandles();
        List<String> tabTitles = new ArrayList<>();

        for (String window: windows) {
            driver.switchTo().window(window);
            tabTitles.add(driver.getTitle());
            driver.close();
        }

        for (String titles: tabTitles ) {
            System.out.println(titles);
        }

    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    public static class JavaStreams {
        public static void main(String[] args){
            //Without Streams
            //Count the number of names starting with the alphabet A in list
            ArrayList<String> names = new ArrayList<>();
            names.add("Alex");
            names.add("Don");
            names.add("Andrid");
            names.add("Emilia");
            names.add("Alexandra");
            names.add("Aandreea");

            System.out.println(withoutStreams(names));
            withStreams(names);
            System.out.println(streamFromStrings());
        }
        private static int withoutStreams(ArrayList<String> names){
            int namesCounter = 0;
            for (String name: names) {
                if(name.startsWith("A")){
                    namesCounter++;
                }
            }
            return namesCounter;
        }

        private static void withStreams(ArrayList<String> names){
            //return names.stream().filter(x -> x.startsWith("A")).count();
            names.stream().filter(x -> x.startsWith("A") && x.length() <= 6).forEach(System.out::println);
        }

        private static long streamFromStrings(){
            return Stream.of("Alex","Don","Andrid","Emilia","Alexandra","Aandreea").filter(x -> x.startsWith("A")).count();

        }


    }
}
