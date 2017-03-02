package Selenium_part_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

/**
 * Created by Zakir_Mustafin on 2/3/2017.
 */
public abstract class YaMailAbstract {
    protected static WebDriver driver;

    protected String addressOfLetter = "samsamitch1@yandex.ru";
    protected String textOfLetter = "Мама мыла раму!!!";
    protected String START_URL = "https://mail.yandex.ru/";



    public YaMailAbstract(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            setDriver();
        }
        return driver;
    }

    private static void setDriver() {
//         System.setProperty("webdriver.ie.driver", "C:\\Data\\For_grid\\IEDriverServer.exe");
//        driver = new InternetExplorerDriver();
//
//		 System.setProperty("webdriver.gecko.driver", "C:\\Data\\For_grid\\geckodriver.exe");
//        driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "C:\\Data\\For_grid\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void highlightElement(WebDriver driver, WebElement element)
    {
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
// take screenshot here
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
    }


    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

}
