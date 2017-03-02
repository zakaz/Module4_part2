package Selenium_part_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Zakir_Mustafin on 2/4/2017.
 */
public class CheckSentFolder extends YaMailAbstract {

    private By subjectElement;
    private WebDriverWait wait;


    @FindBy(xpath = "//a[contains(@href, '#sent')]")
    WebElement sentButton;

    public CheckSentFolder(WebDriver driver) {
        super(driver);
    }

    public boolean goToSentFolderCheckTheLetterExsist(String subject){
        wait = new WebDriverWait(driver, 10);
        subjectElement = By.xpath("//span[contains(@title, '" +subject+ "')]");
        sentButton.click();
        wait.until(ExpectedConditions.titleIs("Отправленные — Яндекс.Почта"));
        return driver.findElement(subjectElement).isDisplayed();
    }
}
