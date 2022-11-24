package pages;

import core.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    /*
        Locators
     */

    //Username field
    private final By usernameField = By.xpath("//input[@id='userEmail']");

    //Password field
    private final By passwordField = By.xpath("//input[@id='userPassword']");

    //Login button
    private final By loginButton = By.xpath("//input[@id='login']");

    /*
        Functionality
     */

    //Fill username field
    public void fillUsernameField(String username){
        sendKeys(usernameField,username);
    }

    //Fill password field
    public void fillPasswordField(String password){
        sendKeys(passwordField,password);
    }

    //Click the login button
    public void clickLoginButton(){
        click(loginButton);
    }
}
