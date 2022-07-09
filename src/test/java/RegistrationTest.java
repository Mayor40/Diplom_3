import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObject.CredentialsPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;
import userClient.UserClient;
import userModel.User;
import userModel.UserCredentials;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class RegistrationTest {

    private UserClient userClient;
    private String accessToken;

    @Before
    public void init() {
        Configuration.browserSize = "1920x1080";
        browser = System.getProperty("selenide.browser", "chrome");
        userClient = new UserClient();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken).then().log().all();
        }
    }

    @Test
    @DisplayName("Check successful registration")
    public void successRegistrationTest() {
        User user = User.getRandom();
        MainPage mainPage = open(MainPage.MAIN, MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        RegistrationPage registrationPage = credentialsPage.register();
        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword(user.getPassword());
        registrationPage.register();
        webdriver().shouldHave(url(CredentialsPage.CREDS));
        UserCredentials creds = UserCredentials.from(user);
        accessToken = userClient.login(creds).then().log().all().extract().path("accessToken");
    }

    @Test
    @DisplayName("Check errormessage about short password")
    public void incorrectPasswordTest() {
        User user = User.getUserWithShortPassword();
        MainPage mainPage = open(MainPage.MAIN, MainPage.class);
        CredentialsPage credentialsPage = mainPage.enterAccount();
        RegistrationPage registrationPage = credentialsPage.register();
        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword(user.getPassword());
        registrationPage.register();
        String message = registrationPage.errorMessageText();
        Assert.assertEquals("Некорректный пароль", message);

        if (user.getPassword().length() > 5){
            UserCredentials creds = UserCredentials.from(user);
            accessToken = userClient.login(creds).then().log().all().extract().path("accessToken");
        }
    }
}
