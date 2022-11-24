package pages;

import flows.AuthenticationFlow;
import org.openqa.selenium.WebDriver;

public class Pages {
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public AuthenticationFlow authenticationFlow;

    public Pages(WebDriver driver){
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        authenticationFlow = new AuthenticationFlow(driver);

    }

}
