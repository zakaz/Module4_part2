package Selenium_part_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 * Created by Zakir_Mustafin on 2/3/2017.
 */
public abstract class YaMailAbstract {
    protected WebDriver driver;

    protected String addressOfLetter = "samsamitch1@yandex.ru";
    protected String textOfLetter = "Мама мыла раму!!!";
    private static final String START_URL = "https://mail.yandex.ru/";



    public YaMailAbstract(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebDriver getDriver() {
        if (driver == null) {
            setDriver();
        }
        return driver;
    }

    private void setDriver() {
//         System.setProperty("webdriver.ie.driver", "C:\\Data\\For_grid\\IEDriverServer.exe");
//         DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//
//		 System.setProperty("webdriver.gecko.driver", "C:\\Data\\For_grid\\geckodriver.exe");
//		 DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zakir_mustafin@epam.com\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
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

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zakir_mustafin@epam.com\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
//        driver = YaMailAbstract.getDriver();
//        driver = new ChromeDriver();
//        driver.get(START_URL);
        getDriver().get(START_URL);
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }
}
