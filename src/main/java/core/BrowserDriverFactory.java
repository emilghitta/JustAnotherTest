package core;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class BrowserDriverFactory {

    public WebDriver createDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/GlobalData.properties");
        properties.load(fileInputStream);
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        String executionMode = System.getProperty("executionMode");
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        WebDriver driver;
        switch (browser.toUpperCase()){
            case "FIREFOX":
                System.out.println("Launching Firefox browser");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                if(executionMode.equals("headless")){
                    firefoxOptions.addArguments(executionMode);
                    System.out.println("Starting in headless mode");
                }

                driver = new FirefoxDriver(firefoxOptions);

                driver.manage().window().setSize(new Dimension(screenWidth,screenHeight));
                break;

            case "CHROME":
                System.out.println("Launching Chrome browser");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability(CapabilityType.BROWSER_NAME,"chrome");
                WebDriverManager.chromedriver().setup();
                if(executionMode == null){
                    chromeOptions.addArguments("headed");
                }else if(executionMode.equals("headless")){
                        chromeOptions.addArguments(executionMode);
                        System.out.println("Starting in headless mode");
                }
                //driver = new RemoteWebDriver(new URL("http://169.254.132.8:4444"),chromeOptions);
                driver = new ChromeDriver();
                driver.manage().window().setSize(new Dimension(screenWidth,screenHeight));
                break;
            default:
                System.out.println("Fallback to default browser (Edge)");
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability(CapabilityType.BROWSER_NAME,"MicrosoftEdge");
                WebDriverManager.edgedriver().setup();
                if(executionMode == null){
                    edgeOptions.addArguments("headed");
                }else if(executionMode.equals("headless")){
                    edgeOptions.addArguments(executionMode);
                    System.out.println("Starting in headless mode");
                }
                driver = new RemoteWebDriver(new URL("http://169.254.132.8:4444"),edgeOptions);
                driver.manage().window().setSize(new Dimension(screenWidth,screenHeight));
        }

        System.out.println("Max screen width is: " + screenWidth + " Max screen height is: " + screenHeight);
        return driver;
    }
}
