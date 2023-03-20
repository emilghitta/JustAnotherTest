package pages;

import core.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JSFailurePage extends BasePageObject {
    public JSFailurePage(WebDriver driver){
        super(driver);
    }

    private final By virtualLibraryButton = By.xpath("//button[@routerlink='/library']");

    public void clickOnLibraryButton(){
        click(virtualLibraryButton);
    }

}
