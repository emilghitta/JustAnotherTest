package flows;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class AuthenticationFlow extends LoginPage {
    public AuthenticationFlow(WebDriver driver){
        super(driver);
    }

    public void authenticate(String username, String password){
        fillUsernameField(username);
        fillPasswordField(password);
        clickLoginButton();
    }

}
