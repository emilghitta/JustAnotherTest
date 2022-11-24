package GeneralSeleniumFunctionalityTests;

import core.TestUtilities;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class NameFilter extends TestUtilities {

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

}
