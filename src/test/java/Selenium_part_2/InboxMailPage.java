package Selenium_part_2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Zakir_Mustafin on 2/3/2017.
 */
public class InboxMailPage extends YaMailAbstract{

    @FindBy (xpath = "//span[text()='Входящие']")
    WebElement wordInboxInMailbox;

    @FindBy (linkText = "Написать")
    WebElement buttonCreateNewLetter;

//    WebElement draggable = driver.findElement(By.xpath("//div[@count='2']/div/a/div/span/span[position()=2]"));
    @FindBy (xpath = "//div[5]/div[1]/div[@count='2']/div/a/div/span/span[position()=2]/span[text()='Сам Самыч']")
    WebElement draggable;

    @FindBy (xpath = "//span[text()='просто_папка']")
    WebElement droppable;

    @FindBy (xpath = "//div[@data-params='source=toolbar'][19]")
    WebElement elementForContexClick;

    public InboxMailPage(WebDriver driver){
        super(driver);
    }

    public EnterDetailsOfNewLetter createNewMail() throws InterruptedException {
        Thread.sleep(3000);


        highlightElement(getDriver(), buttonCreateNewLetter);
        buttonCreateNewLetter.click();

       return new EnterDetailsOfNewLetter (getDriver());
    }


    public boolean loginIsCorrect(){
        return wordInboxInMailbox.isDisplayed();
    }


}
