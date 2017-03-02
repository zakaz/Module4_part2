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
public class LogOutFromMailbox extends YaMailAbstract {

    private WebDriverWait wait;

    @FindBy(css = ".mail-User-Picture.js-user-picture")
    WebElement clickOnUserImage;

    @FindBy(linkText = "Выход")
    WebElement clickOnExitButton;

    @FindBy(xpath = "//button[@type='submit']/span[text()='Войти']")
    WebElement checkThatThisElementPresented;

    public LogOutFromMailbox(WebDriver driver) {
        super(driver);
    }

    public void logOut(){
        wait = new WebDriverWait(getDriver(), 10);
        clickOnUserImage.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-user-dropdown-content")));
        clickOnExitButton.click();
    }

    public boolean checkThatExitTrue(){
        return checkThatThisElementPresented.isDisplayed();
    }
}
