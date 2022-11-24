package pages;

import core.BasePageObject;
import messages.DashboardPageMessages;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePageObject {
    public DashboardPage(WebDriver driver){
        super(driver);
    }

    /*
    Locators
     */


    /*
    Functionality
     */

    public String getPageURL(){
        return getCurrentURL(DashboardPageMessages.DASHBOARD_PAGE_URL);
    }



}
