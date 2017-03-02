package Selenium_part_2;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Zakir_Mustafin on 2/4/2017.
 */
public class SendingMail extends YaMailAbstract{

    private By subjectElement;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[text()='Черновики']")
    WebElement clickDraftButton;

    @FindBy(xpath = "//span[text()='Отправить']")
    WebElement sendButton;


    public SendingMail(WebDriver driver) {
        super(driver);
    }


    public CheckSentFolder sendMail(String subject){
        wait = new WebDriverWait(driver, 10);
        subjectElement = By.xpath("//span[contains(@title, '" +subject+ "')]");
        driver.findElement(subjectElement).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Отправить']")));
        sendButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mail-Done-Title.js-title-info")));
        clickDraftButton.click();
        wait.until(ExpectedConditions.titleIs("Черновики — Яндекс.Почта"));
        return new CheckSentFolder(driver);
    }

    public boolean checkThatSentLetterDisapearedFromDraft(){
        try {
            return driver.findElement(subjectElement).isDisplayed();
        } catch (NullPointerException e){
            System.out.println("Didn't find such subject in draft folder" + e);
            return false;
        }
    }
}
