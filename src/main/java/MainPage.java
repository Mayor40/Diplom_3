import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    //локатор кнопки Войти в аккаунт
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement enterAccountButton;

    //локатор кнопки Оформить заказ
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    public SelenideElement orderBurgerButton;

    //локатор раздела Булки
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bulkiSection;

    //локатор заголовка Начинки
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bulkiHeader;

    //локатор раздела Соусы
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sausesSection;

    //локатор заголовка Соусы
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement sausesHeader;

    //локатор раздела Начинки
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsSection;

    //локатор заголовка Начинки
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingsHeader;

    //метод  нажатия кнопки "Войти в аккаунт"
    public CredentialsPage enterAccount() {
        enterAccountButton.click();
        return page(CredentialsPage.class);
    }

    //переход к разделу Булки
    public void moveToBulki() {
        bulkiSection.click();
    }

    public String bulkiHeaderText() {
        String header = bulkiHeader.getText();
        return header;
    }

    //переход к разделу Соусы
    public void moveToSauses() {
        sausesSection.click();
    }

    public String sausesHeaderText() {
        String header = sausesHeader.getText();
        return header;
    }

    //переход к разделу Начинки
    public void moveToFillings() {
        fillingsSection.click();
    }

    public String fillingsHeaderText() {
        String header = fillingsHeader.getText();
        return header;
    }

    //получить текст кнопки Оформить заказ
    public String getOrderButtonText() {
        String buttonText = orderBurgerButton.getText();
        return buttonText;
    }


}
