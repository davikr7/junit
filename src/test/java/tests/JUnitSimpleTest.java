package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.data.Locale;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class JUnitSimpleTest {

    @DisplayName("Демонстрационный тест")
    @Test
    void simpleTest() {
        Assertions.assertTrue(3 > 2);
    }

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        open("https://google.com/");
    }

//    @CsvSource(value = {
//            "Selenide, https://selenide.org",
//            "Allure testops, https://qameta.io"
//    })
//
//// OR!
//    @CsvFileSource(resources = "/testData.csv")

    // если всего один аргумент (String productName), то используем ValueSource
    @ValueSource(
            strings = {"Selenide", "Allure testops"}
    )

    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче гугла по запросу {0} ")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void checkSelenide(String productName) {
        $("[name=q]").setValue(productName).pressEnter();
        $$("div[class='g']").shouldHave(CollectionCondition.sizeGreaterThan(5));
//        $("[id=search]").shouldHave(text(productUrl));

    }

    void selenideSiteShouldContainAllOfButtonsForGivenLocale(Locale locale, List<String> buttons) {

    }

}
