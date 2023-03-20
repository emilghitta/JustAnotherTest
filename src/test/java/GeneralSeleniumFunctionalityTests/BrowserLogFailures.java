package GeneralSeleniumFunctionalityTests;

import core.TestUtilities;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BrowserLogFailures extends TestUtilities {

    @Parameters({"pageURL"})
    @Test
    public void logJSFailures(String pageURL){
        driver.get(pageURL);
        pages.jsFailurePage.clickOnLibraryButton();
        Assert.fail();
    }

}
