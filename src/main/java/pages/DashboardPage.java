package pages;

import core.BasePageObject;
import messages.DashboardPageMessages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage extends BasePageObject {
    public DashboardPage(WebDriver driver){
        super(driver);
    }

    /*
    Locators
     */
    private final By searchField = By.xpath("//section[@id='sidebar']/form//input[@name='search']");
    private final By products = By.xpath("//h5/b");
    /*

    Functionality
     */

    public String getPageURL(){
        return getCurrentURL(DashboardPageMessages.DASHBOARD_PAGE_URL);
    }

    public void sendKeysToSearchField(String searchInput){
        sendKeys(searchField,searchInput);
    }

    public List<WebElement> getProducts(){
        return findElements(products);
    }





}
