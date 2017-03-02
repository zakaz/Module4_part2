package capabilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zakir_Mustafin on 2/11/2017.
 */
public class GetDriverCapabilities {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        // required to get single WebDriver instance for the whole test flow
        // and make convenient WebDriver call from other classes
        if (driver == null) {
            setDriver();
        }
        return driver;
    }

    private static void setDriver() {


//         System.setProperty("webdriver.ie.driver", "C:\\Data\\For_grid\\IEDriverServer.exe");
//         DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//
//		 System.setProperty("webdriver.gecko.driver", "C:\\Data\\For_grid\\geckodriver.exe");
//		 DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//
        System.setProperty("webdriver.chrome.driver", "C:\\Data\\For_grid\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        capabilities.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
