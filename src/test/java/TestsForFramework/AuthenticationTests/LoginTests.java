package TestsForFramework.AuthenticationTests;

import core.Retry;
import core.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends TestUtilities {

    @Parameters({"username","password","dashboardURL"})
    @Test(retryAnalyzer = Retry.class)
    void successfullyLoginTest(String username, String password, String dashboardURL) throws IOException {

        System.out.println("Testing with valid credentials: " +
                "username: " + username +
                " and password: " + password);

        pages.authenticationFlow.authenticate(username,password);

        System.out.println(getsimpleJSON().get("username"));

        Assert.assertEquals(pages.dashboardPage.getPageURL(),dashboardURL,"Incorrect page URL after authentication!");
    }
}
