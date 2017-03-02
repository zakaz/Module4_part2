package Selenium_part_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Zakir_Mustafin on 2/3/2017.
 */
public abstract class YaMailAbstract {
    protected WebDriver driver;

    protected String addressOfLetter = "samsamitch1@yandex.ru";
    protected String textOfLetter = "Мама мыла раму!!!";


    public YaMailAbstract(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zakir_mustafin@epam.com\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        driver = new ChromeDriver();
        return this.driver;
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
