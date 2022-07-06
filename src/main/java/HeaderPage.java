import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class HeaderPage {
    //локатор кнопки Личный кабинет

    @FindBy(how = How.XPATH, using = "html/body/div/div/header/nav/a/p")
    private SelenideElement personalAccountButton;

    //локатор кнопки Конструктор
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    //локатор логотипа "html/body/div/div/header/nav/div/a/svg"
    @FindBy(how = How.XPATH, using = "html/body/div/div/header/nav/div")
    private SelenideElement burgersLogo;

    //переход в личный кабинет после авторизации по кнопке Личный кабинет
    public AccountPage clickAccountButton() {
        personalAccountButton.click();
        return page(AccountPage.class);
    }

    //переход на страницу авторизации по кнопке Личный кабинет
    public CredentialsPage clickAccountButtonToAuthorize() {
        personalAccountButton.click();
        return page(CredentialsPage.class);
    }

    //переход на главную по нажатию на лого после авторизации
    public void logoClick() {
        burgersLogo.click();
    }

    //переход на главную по нажатию на кнопку Конструктор после авторизации
    public void builderClick() {
        constructorButton.click();
    }

}
