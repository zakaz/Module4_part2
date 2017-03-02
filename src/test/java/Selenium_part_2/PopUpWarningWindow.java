package Selenium_part_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Zakir_Mustafin on 2/3/2017.
 */
public class PopUpWarningWindow extends YaMailAbstract{

    @FindBy(xpath = "//button[@data-action='save']")
    WebElement saveButtonExsist;

    public PopUpWarningWindow(WebDriver driver) {
        super(driver);
    }

    public CheckDraftFolder savingLetterToDraft(){
        saveButtonExsist.click();
        return new CheckDraftFolder(driver);
    }

    public boolean saveButtonExsist(){
        return saveButtonExsist.isDisplayed();
    }
}
