import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.*;
import userClient.UserClient;
import userModel.User;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest {

    private MainPage mainPage;
    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void init() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        mainPage = open(MainPage.MAIN, MainPage.class);
        userClient = new UserClient();
        user = User.getRandom();
        accessToken = userClient.create(user).then().log().all().extract().path("accessToken");
    }

    @After
    public void tearDown() {
        WebDriverRunner.getWebDriver().close();

        if (accessToken != null) {
            userClient.delete(accessToken).then().log().all();
        }
    }

    @Test
    @DisplayName("Check authorization using button Enter")
    public void enterButtonLoginTest() {
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail(user.getEmail());
        credentialsPage.enterPassword(user.getPassword());
        credentialsPage.authorize();
        String buttonText = mainPage.getOrderButtonText();
        MatcherAssert.assertThat(buttonText, equalTo("Оформить заказ"));
    }

    @Test
    @DisplayName("Check authorization using button Account in the header")
    public void accountButtonLoginTest() {
        CredentialsPage credentialsPage = mainPage.clickAccountButtonToAuthorize();
        credentialsPage.enterEmail(user.getEmail());
        credentialsPage.enterPassword(user.getPassword());
        MainPage mainPage = credentialsPage.authorize();
        String buttonText = mainPage.getOrderButtonText();
        MatcherAssert.assertThat(buttonText, equalTo("Оформить заказ"));
    }

    @Test
    @DisplayName("Check authorization using registration page")
    public void registrationPageLoginTest() {
        CredentialsPage credentialsPage = mainPage.enterAccount();
        RegistrationPage registrationPage = credentialsPage.register();
        CredentialsPage credentialsPage1 = registrationPage.clickEnterReference();
        credentialsPage1.enterEmail(user.getEmail());
        credentialsPage1.enterPassword(user.getPassword());
        MainPage mainPage1 = credentialsPage.authorize();
        String buttonText = mainPage1.getOrderButtonText();
        MatcherAssert.assertThat(buttonText, equalTo("Оформить заказ"));
    }

    @Test
    @DisplayName("Check authorization using restorepassword page")
    public void restorePasswordPageLoginTest() {
        CredentialsPage credentialsPage = mainPage.enterAccount();
        RestorePasswordPage restorePasswordPage = credentialsPage.restore();
        CredentialsPage credentialsPage1 = restorePasswordPage.loginUrlClick();
        credentialsPage1.enterEmail(user.getEmail());
        credentialsPage1.enterPassword(user.getPassword());
        MainPage mainPage1 = credentialsPage.authorize();
        String buttonText = mainPage1.getOrderButtonText();
        MatcherAssert.assertThat(buttonText, equalTo("Оформить заказ"));
    }
}
