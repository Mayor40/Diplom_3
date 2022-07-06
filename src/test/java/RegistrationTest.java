import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class RegistrationTest {
    @Before
    public void init() {
        Configuration.startMaximized = true;
        Configuration.browser = System.getProperty("selenide.browser", "firefox");
    }

    @Test
    @DisplayName("Check successful registration")
    public void successRegistrationTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        RegistrationPage registrationPage = credentialsPage.register();
        registrationPage.enterName(RandomStringUtils.randomAlphanumeric(10));
        registrationPage.enterEmail(RandomStringUtils.randomAlphanumeric(10) + "@mail.ru");
        registrationPage.enterPassword(RandomStringUtils.randomAlphanumeric(10));
        registrationPage.register();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));

    }

    @Test
    @DisplayName("Check errormessage about short password")
    public void incorrectPasswordTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        RegistrationPage registrationPage = credentialsPage.register();
        registrationPage.enterName("Дима");
        registrationPage.enterEmail("mayor@mail.ru");
        registrationPage.enterPassword("Qwe12");
        registrationPage.register();
        String message = registrationPage.errorMessageText();
        Assert.assertEquals("Некорректный пароль", message);
    }


}
