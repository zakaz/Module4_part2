package Selenium_part_2;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Zakir_Mustafin on 2/3/2017.
 */
public class EnterDetailsOfNewLetter extends YaMailAbstract{

    @FindBy (xpath = "//span[text()='Отправить']")
    WebElement checkButtonSendExsist;

    @FindBy (css = ".js-compose-field.mail-Bubbles.mail-Bubbles-Sms_hidden")
    WebElement addressForSending;

    @FindBy(xpath = "//input[@name='subj']")
    WebElement subjectForLetter;

    @FindBy(xpath = "//div[@role='textbox']")
    WebElement textForLetter;

    @FindBy(xpath = "//*[@title=\"Закрыть\"]")
    WebElement closeButton;

    public EnterDetailsOfNewLetter(WebDriver driver) {
        super(driver);
    }

    public PopUpWarningWindow enterDetailsOfTheLetter(String address, String subject, String textOfMail){
        JavascriptExecutor executor = ((JavascriptExecutor) driver);

        addressForSending.sendKeys(address);
        subjectForLetter.sendKeys(subject);
        textForLetter.sendKeys(textOfMail);
//        closeButton.click();
        executor.executeScript("arguments[0].click();", closeButton);



        return new  PopUpWarningWindow(driver);
    }

    public boolean sendButtonExsist(){
        return checkButtonSendExsist.isDisplayed();
    }
}
