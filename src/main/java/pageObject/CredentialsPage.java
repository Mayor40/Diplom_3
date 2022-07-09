package pageObject;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class CredentialsPage {

    public static final String CREDS = "https://stellarburgers.nomoreparties.site/login";

    // локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//input[@name ='name']")
    private SelenideElement emailField;

    // локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@name ='Пароль']")
    private SelenideElement passwordField;

    //локатор кнопки Войти
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement enterButton;

    //локатор ссылки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Зарегистрироваться')]")
    private SelenideElement regURL;

    //локатор ссылки Восстановить пароль
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Восстановить пароль')]")
    private SelenideElement restorePasswordURL;

    //заполнение поля Email
    public void enterEmail(String email) {
        emailField.shouldBe(visible);
        emailField.click();
        emailField.setValue(email);
    }

    //заполнение поля Пароль
    public void enterPassword(String password) {
        passwordField.click();
        passwordField.setValue(password);
    }

    //нажатие кнопки Войти - для проверки успешной авторизации
    public MainPage authorize() {
        enterButton.scrollIntoView(true);
        enterButton.click();
        return page(MainPage.class);
    }

    //нажатие кнопки Войти - для проверки перехода в личный кабинет
    public HeaderPage login() {
        enterButton.click();
        return page(HeaderPage.class);
    }

    //метод перехода на страницу регистрации со страницы авторизации
    public RegistrationPage register() {
        regURL.click();
        return page(RegistrationPage.class);
    }

    //метод перехода на страницу восстановления пароля
    public RestorePasswordPage restore() {
        restorePasswordURL.click();
        return page(RestorePasswordPage.class);
    }
}
