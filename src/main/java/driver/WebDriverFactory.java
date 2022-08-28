package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class WebDriverFactory {

static String browserForJenkins = System.getenv("BROWSER");

    public static WebDriver initDriver(Browsers browser) {
        ChromeOptions chromeOptions = new  ChromeOptions();
        if (browserForJenkins != null) {
            chromeOptions.getCapability(browserForJenkins);
        }else {
            switch (browser) {
                case EDGE: {
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver();
                }
                case CHROME: {
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
                }
                case FIREFOX: {
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                }
            }
        }
        return null;
    }

    public static WebDriver initDriver() {
        String browserName = System.getProperty("browserName", "chrome");
        try {
            return initDriver(Browsers.valueOf(browserName.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.err.println("This browser is not supported!!!");
            System.exit(-1);
        }
        return null;
    }
}
