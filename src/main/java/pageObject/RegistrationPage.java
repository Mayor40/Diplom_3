package pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {


    // локатор поля "Имя"
    @FindBy(how = How.XPATH, using = ".//fieldset[1]//input")
    private SelenideElement nameField;

    // локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement emailField;

    // поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordField;

    //локатор кнопки Войти
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement enterReference;

    //локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;

    //локатор сообщения об ошибке
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Некорректный пароль')]")
    private SelenideElement incorrectPasswordMessage;

    //заполнение поля Имя
    public void enterName(String name) {
        nameField.click();
        nameField.setValue(name);
    }

    //заполнение поля Email
    public void enterEmail(String email) {
        emailField.click();
        emailField.setValue(email);
    }

    //заполнение поля Пароль
    public void enterPassword(String password) {
        passwordField.click();
        passwordField.setValue(password);
    }

    //нажатие кнопки Зарегистрироваться
    public void register() {
        registrationButton.click();
    }


    //получение сообщения об ошибке
    public String errorMessageText() {
        String message = incorrectPasswordMessage.getText();
        return message;
    }

    //нажатие кнопки Войти
    public CredentialsPage clickEnterReference() {
        enterReference.click();
        return page(CredentialsPage.class);
    }
}
