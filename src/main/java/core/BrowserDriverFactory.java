package core;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowserDriverFactory {

    public WebDriver createDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/GlobalData.properties");
        properties.load(fileInputStream);
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");


        WebDriver driver;
        switch (browser.toUpperCase()){
            case "FIREFOX":
                System.out.println("Launching Firefox browser");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "CHROME":
                System.out.println("Launching Chrome browser");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                System.out.println("Fallback to default browser (Edge)");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
        }
        return driver;
    }
}
