package flows;

import org.openqa.selenium.WebDriver;
import pages.DashboardPage;

public class ProductSearchFlow extends DashboardPage {
    public ProductSearchFlow(WebDriver driver){
        super(driver);
    }

    public void searchProduct(String keyword){
        sendKeysToSearchField(keyword);
        hitEnterKey();
    }

}
