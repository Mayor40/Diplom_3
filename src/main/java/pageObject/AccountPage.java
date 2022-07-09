package pageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPage {

    public static final String ACCOUNT = "https://stellarburgers.nomoreparties.site/account/profile";

    //локатор кнопки Войти в аккаунт
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitButton;

    //нажатие кнопки Выход
    public void exitButtonClick() {
        exitButton.click();
    }
}
