package Selenium_part_2;

import capabilities.GetDriverCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Zakir_Mustafin on 2/3/2017.
 */
public class YaMailTest{
    private static final String LOGIN = "samsamitch1@yandex.ru";
    private static final String PASSWORD = "TestNG_password";
    private static final String START_URL = "https://mail.yandex.ru/";

    private static final String ADDRESS = "samsamitch1@yandex.ru";
    private static final String SUBJECTFORLETTER = String.valueOf(System.currentTimeMillis());
    private static final String TEXTFORLETTER = "Мама мыла раму!!!";


    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        YaMailAbstract.getDriver().get(START_URL);

        YaMailAbstract.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        YaMailAbstract.getDriver().manage().window().maximize();
    }

    @Test(description = "Check valid Title")
    public void checkTitle(){
        String expectedTitle = "Яндекс.Почта — бесплатная электронная почта";
        String actualTitle =  YaMailAbstract.getDriver().getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(dependsOnMethods = "checkTitle", description = "Login to Mail.Yandex account")
    public void loginToYaMail() {
        // Login via user-defined method
        new SignInPage(YaMailAbstract.getDriver()).loginToYaMail(LOGIN, PASSWORD);

        // Verify the login procedure was correct
        Assert.assertTrue(new InboxMailPage(YaMailAbstract.getDriver()).loginIsCorrect(), "Looks you are NOT logged in correctly!");
        System.out.println("Login was completed correctly.");
    }

    @Test(dependsOnMethods = "loginToYaMail", description = "Create new Letter")
    public void createNewLetter() throws InterruptedException {
        // Login via user-defined method
        new InboxMailPage(YaMailAbstract.getDriver()).createNewMail();

        // Verify the login procedure was correct
        Assert.assertTrue(new EnterDetailsOfNewLetter(YaMailAbstract.getDriver()).sendButtonExsist(), "Looks you are on the wrong Page!");
        System.out.println("Window for creating new Mail opened correctly.");
    }

    @Test(dependsOnMethods = "createNewLetter", description = "Type address, subject and main Text of the letter")
    public void typeAddressSubjTextOfLetter() {
        new EnterDetailsOfNewLetter(YaMailAbstract.getDriver()).enterDetailsOfTheLetter(ADDRESS, SUBJECTFORLETTER, TEXTFORLETTER);

        // Verify the login procedure was correct
        Assert.assertTrue(new PopUpWarningWindow(YaMailAbstract.getDriver()).saveButtonExsist(), "Pop-up window didn't appear with Save button");
        System.out.println("Save button displayed correctly.");
    }

    @Test(dependsOnMethods = "typeAddressSubjTextOfLetter", description = "click on save button, check letter in draft")
    public void clickSaveButtonCheckLetterInDraft(){
        new PopUpWarningWindow(YaMailAbstract.getDriver()).savingLetterToDraft().clickOnDraftButton();
        Assert.assertTrue(new CheckDraftFolder(YaMailAbstract.getDriver()).checkLetterExsistInDraft(ADDRESS, SUBJECTFORLETTER, TEXTFORLETTER));
        System.out.println("Letter saved in draft folder!");
    }

    @Test(dependsOnMethods = "clickSaveButtonCheckLetterInDraft", description = "send letter and check draft again")
    public void sendLetterCheckDraftAgain(){
        new SendingMail(YaMailAbstract.getDriver()).sendMail(SUBJECTFORLETTER);
        Assert.assertFalse(new SendingMail(YaMailAbstract.getDriver()).checkThatSentLetterDisapearedFromDraft());
        System.out.println("Letter disapeared from draft folder!");
    }

    @Test(dependsOnMethods = "sendLetterCheckDraftAgain", description = "check that letter exsist in sent folder")
    public void checkLetterInSentFolder(){
        Assert.assertTrue(new CheckSentFolder(YaMailAbstract.getDriver()).goToSentFolderCheckTheLetterExsist(SUBJECTFORLETTER));
        System.out.println("Letter exsist in sent folder!");
    }

    @Test(dependsOnMethods = "checkLetterInSentFolder", description = "Exit and check that exit true")
    public void exitAndCheckThatExitDone(){
        new LogOutFromMailbox(YaMailAbstract.getDriver()).logOut();
        Assert.assertTrue(new LogOutFromMailbox(YaMailAbstract.getDriver()).checkThatExitTrue());
        System.out.println("LogOut done successfully!");
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        YaMailAbstract.driver.quit();
    }

}
