import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {


    //поля для ввода учетных данных
    private ElementsCollection regFields = $$(byXpath("html/body/div/div/main/div/form/fieldset/div/div/input"));

    //локатор кнопки Войти
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement enterReference;

    //локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;

    //локатор сообщения об ошибке
    @FindBy(how = How.XPATH, using = "html/body/div/div/main/div/form/fieldset/div/p")
    private SelenideElement incorrectPasswordMessage;

    //заполнение поля Имя
    public void enterName(String name) {
        regFields.get(0).click();
        regFields.get(0).setValue(name);
    }

    //заполнение поля Email
    public void enterEmail(String email) {
        regFields.get(1).click();
        regFields.get(1).setValue(email);
    }

    //заполнение поля Пароль
    public void enterPassword(String password) {
        regFields.get(2).click();
        regFields.get(2).setValue(password);
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
