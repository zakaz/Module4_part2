package Selenium_part_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Zakir_Mustafin on 2/4/2017.
 */
public class CheckDraftFolder extends YaMailAbstract{

    private By addressElement;
    private By subjectElement;
    private By textElement;

    @FindBy(xpath = "//span[text()='Черновики']")
    WebElement draftButton;

    public CheckDraftFolder(WebDriver driver) {
        super(driver);
    }

    protected SendingMail clickOnDraftButton(){
        draftButton.click();
//        wait.until(ExpectedConditions.titleIs("Черновики — Яндекс.Почта"));
        return new SendingMail(getDriver());
    }

    public boolean checkLetterExsistInDraft(String address, String subject, String textOfMail){
        addressElement = By.xpath("//span[@title='" +address+ "']");
        subjectElement = By.xpath("//span[text()='" + subject + "']");
        textElement = By.xpath("//span[text()='" +textOfMail+ "']");

        if (getDriver().findElement(addressElement).isDisplayed() && getDriver().findElement(subjectElement).isDisplayed() &&
                getDriver().findElement(textElement).isDisplayed()){
            return true;
        } else return false;
    }
}
