import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class CredentialsPage {

    //поля для ввода данных
    private ElementsCollection logPass = $$(byXpath("html/body/div/div/main/div/form/fieldset/div/div/input"));

    //локатор кнопки Войти
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement enterButton;

    private ElementsCollection references = $$(byXpath("html/body/div/div/main/div/div/p/a"));

    //локатор ссылки Зарегистрироваться
    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    private SelenideElement regURL;

    //локатор ссылки Восстановить пароль
    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    private SelenideElement restorePasswordURL;

    //заполнение поля Email
    public void enterEmail(String email) {
        logPass.get(0).shouldBe(Condition.visible);
        logPass.get(0).click();
        logPass.get(0).setValue(email);
    }

    //заполнение поля Пароль
    public void enterPassword(String password) {
        logPass.get(1).click();
        logPass.get(1).setValue(password);
    }

    //нажатие кнопки Войти - для проверки успешной авторизации
    public MainPage authorize() {
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
        references.get(0).click();
        return page(RegistrationPage.class);
    }

    //метод перехода на страницу восстановления пароля
    public RestorePasswordPage restore() {
        references.get(1).click();
        return page(RestorePasswordPage.class);
    }


}
