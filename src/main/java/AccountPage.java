import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AccountPage {
    //локатор кнопки Войти в аккаунт
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitButton;

    //нажатие кнопки Выход
    public void exitButtonClick() {
        exitButton.click();
    }
}
