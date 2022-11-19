import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageObject.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private MainPage mainPage;

    @Before
    public void init() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        mainPage = open(MainPage.MAIN, MainPage.class);
    }

    @Test
    @DisplayName("Check moving to section Bulki in burger's builder")
    public void moveToBulkiTest() {

        mainPage.moveToSauses();
        mainPage.moveToBulki();
        assertTrue(mainPage.bulkiListDisplayed());
    }

    @Test
    @DisplayName("Check moving to section Sauses in burger's builder")
    public void moveToSausesTest() {
        mainPage.moveToSauses();
        assertTrue(mainPage.sauseListDisplayed());
    }

    @Test
    @DisplayName("Check moving to section Fillings in burger's builder")
    public void moveToFillingsTest() {
        mainPage.moveToFillings();
        assertTrue(mainPage.fillingsListDisplayed());
    }
}
