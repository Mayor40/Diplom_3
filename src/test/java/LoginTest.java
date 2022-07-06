import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest {
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
    @DisplayName("Check authorization using button Enter")
    public void enterButtonLoginTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail("dmitriy85mayor@gmail.com");
        credentialsPage.enterPassword("Dl13111985");
        credentialsPage.authorize();
        String buttonText = mainPage.getOrderButtonText();
        MatcherAssert.assertThat(buttonText, equalTo("Оформить заказ"));

    }

    @Test
    @DisplayName("Check authorization using button Account in the header")
    public void accountButtonLoginTest() {
        HeaderPage headerPage = open("https://stellarburgers.nomoreparties.site/", HeaderPage.class);
        CredentialsPage credentialsPage = headerPage.clickAccountButtonToAuthorize();
        credentialsPage.enterEmail("dmitriy85mayor@gmail.com");
        credentialsPage.enterPassword("Dl13111985");
        MainPage mainPage = credentialsPage.authorize();
        String buttonText = mainPage.getOrderButtonText();
        MatcherAssert.assertThat(buttonText, equalTo("Оформить заказ"));

    }

    @Test
    @DisplayName("Check authorization using registration page")
    public void registrationPageLoginTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        RegistrationPage registrationPage = credentialsPage.register();
        CredentialsPage credentialsPage1 = registrationPage.clickEnterReference();
        credentialsPage1.enterEmail("dmitriy85mayor@gmail.com");
        credentialsPage1.enterPassword("Dl13111985");
        MainPage mainPage1 = credentialsPage.authorize();
        String buttonText = mainPage1.getOrderButtonText();
        MatcherAssert.assertThat(buttonText, equalTo("Оформить заказ"));

    }

    @Test
    @DisplayName("Check authorization using restorepassword page")
    public void restorePasswordPageLoginTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        RestorePasswordPage restorePasswordPage = credentialsPage.restore();
        CredentialsPage credentialsPage1 = restorePasswordPage.loginUrlClick();
        credentialsPage1.enterEmail("dmitriy85mayor@gmail.com");
        credentialsPage1.enterPassword("Dl13111985");
        MainPage mainPage1 = credentialsPage.authorize();
        String buttonText = mainPage1.getOrderButtonText();
        MatcherAssert.assertThat(buttonText, equalTo("Оформить заказ"));

    }

}
