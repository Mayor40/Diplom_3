import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.AccountPage;
import pageObject.CredentialsPage;
import pageObject.HeaderPage;
import pageObject.MainPage;
import userClient.UserClient;
import userModel.User;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PersonalAccountTest {

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
    @DisplayName("Check authorization with entrance to personal account")
    public void moveToPersonalAccountTest() {
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail(user.getEmail());
        credentialsPage.enterPassword(user.getPassword());
        HeaderPage headerPage = credentialsPage.login();
        AccountPage accountPage = headerPage.clickAccountButton();
        webdriver().shouldHave(url(AccountPage.ACCOUNT));
    }

    @Test
    @DisplayName("Check moving from  personal account to main page by clicking Logo")
    public void moveByClickingLogoTest() {
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail(user.getEmail());
        credentialsPage.enterPassword(user.getPassword());
        HeaderPage headerPage = credentialsPage.login();
        AccountPage accountPage = headerPage.clickAccountButton();
        headerPage.logoClick();
        webdriver().shouldHave(url(MainPage.MAIN));
    }

    @Test
    @DisplayName("Check moving from  personal account to main page by clicking builder button")
    public void moveByClickingConstructorTest() {
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail(user.getEmail());
        credentialsPage.enterPassword(user.getPassword());
        HeaderPage headerPage = credentialsPage.login();
        AccountPage accountPage = headerPage.clickAccountButton();
        headerPage.builderClick();
        webdriver().shouldHave(url(MainPage.MAIN));
    }

    @Test
    @DisplayName("Check loging off personal account")
    public void logOutTest() {
        CredentialsPage credentialsPage = mainPage.enterAccount();
        credentialsPage.enterEmail(user.getEmail());
        credentialsPage.enterPassword(user.getPassword());
        HeaderPage headerPage = credentialsPage.login();
        AccountPage accountPage = headerPage.clickAccountButton();
        accountPage.exitButtonClick();
        webdriver().shouldHave(url(CredentialsPage.CREDS));
    }
}
