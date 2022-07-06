import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PersonalAccountTest {
    @Before
    public void init() {
        Configuration.startMaximized = true;
        Configuration.browser = System.getProperty("selenide.browser", "firefox");
    }

    @After
    public void closeBrowser() {
        WebDriverRunner.getWebDriver().close();
    }

    @Test
    @DisplayName("Check authorization with entrance to personal account")
    public void moveToPersonalAccountTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail("dmitriy85mayor@gmail.com");
        credentialsPage.enterPassword("Dl13111985");
        HeaderPage headerPage = credentialsPage.login();
        AccountPage accountPage = headerPage.clickAccountButton();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/account/profile"));
    }

    @Test
    @DisplayName("Check moving from  personal account to main page by clicking Logo")
    public void moveByClickingLogoTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail("dmitriy85mayor@gmail.com");
        credentialsPage.enterPassword("Dl13111985");
        HeaderPage headerPage = credentialsPage.login();
        AccountPage accountPage = headerPage.clickAccountButton();
        headerPage.logoClick();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @DisplayName("Check moving from  personal account to main page by clicking builder button")
    public void moveByClickingConstructorTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail("dmitriy85mayor@gmail.com");
        credentialsPage.enterPassword("Dl13111985");
        HeaderPage headerPage = credentialsPage.login();
        AccountPage accountPage = headerPage.clickAccountButton();
        headerPage.builderClick();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));

    }

    @Test
    @DisplayName("Check loging off personal account")
    public void logOutTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail("dmitriy85mayor@gmail.com");
        credentialsPage.enterPassword("Dl13111985");
        HeaderPage headerPage = credentialsPage.login();
        AccountPage accountPage = headerPage.clickAccountButton();
        accountPage.exitButtonClick();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));

    }


}
