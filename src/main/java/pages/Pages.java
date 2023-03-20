package pages;

import flows.AuthenticationFlow;
import flows.ProductSearchFlow;
import org.openqa.selenium.WebDriver;

public class Pages {
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public JSFailurePage jsFailurePage;
    public AuthenticationFlow authenticationFlow;
    public ProductSearchFlow productSearchFlow;

    public Pages(WebDriver driver){
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        jsFailurePage = new JSFailurePage(driver);
        authenticationFlow = new AuthenticationFlow(driver);
        productSearchFlow = new ProductSearchFlow(driver);
    }

}
