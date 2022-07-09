package pageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RestorePasswordPage {
    //локатор поля Email
    @FindBy(how = How.XPATH, using = ".//label[text()='Email']")
    private SelenideElement emailField;

    //локатор ссылки Войти
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement loginURL;

    //переход на страницу авторизации по ссылке Войти
    public CredentialsPage loginUrlClick() {
        loginURL.click();
        return page(CredentialsPage.class);
    }
}
