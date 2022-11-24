package core;

import messages.DashboardPageMessages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageObject {
    WebDriver driver;

    public BasePageObject(WebDriver driver){
        this.driver = driver;
    }


    //Find and return WebElement
    protected WebElement findElement(By locator){
        waitForVisibility(locator,Duration.ofSeconds(5));
        return driver.findElement(locator);
    }


    //Find and return current page url
    protected String getCurrentURL(String expectedURL){
        waitForPageToLoad(expectedURL,Duration.ofSeconds(5));
        return driver.getCurrentUrl();
    }

    //Find and return WebElement text
    protected String getText(By locator){
        return findElement(locator).getText();
    }

    //SendKeys to element
    protected void sendKeys(By locator,String input){
        findElement(locator).sendKeys(input);
    }

    //Click on WebElement
    protected void click(By locator){
        findElement(locator).click();
    }


    //Waits
    private void waitFor(ExpectedCondition<WebElement> condition, Duration time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(condition);
    }

    private void waitForPageToLoad(String urlToBe, Duration time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.urlToBe(urlToBe));
    }

    private void waitForVisibility(By locator, Duration time){
        try{
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator),time);
        }catch (StaleElementReferenceException | TimeoutException e){
            System.out.println(e);
        }
    }

}
