import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;

public class ConstructorTest {
    @Before
    public void init() {
        Configuration.startMaximized = true;
        Configuration.browser = System.getProperty("selenide.browser", "firefox");
    }

    @Test
    @DisplayName("Check moving to section Bulki in burger's builder")
    public void moveToBulkiTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.moveToSauses();
        mainPage.moveToBulki();
        String header = mainPage.bulkiHeaderText();
        MatcherAssert.assertThat(header, equalTo("Булки"));

    }

    @Test
    @DisplayName("Check moving to section Sauses in burger's builder")
    public void moveToSausesTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.moveToSauses();
        String header = mainPage.sausesHeaderText();
        MatcherAssert.assertThat(header, equalTo("Соусы"));

    }

    @Test
    @DisplayName("Check moving to section Fillings in burger's builder")
    public void moveToFillingsTest() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.moveToFillings();
        String header = mainPage.fillingsHeaderText();
        MatcherAssert.assertThat(header, equalTo("Начинки"));

    }
}
