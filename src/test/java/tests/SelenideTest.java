package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    static Stream<Arguments> selenideLocaleDataProvider(){
        return Stream.of(
                Arguments.of(Locale.EN, List.of( "Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes" ) ),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы") )
        );

    }

    @MethodSource("selenideLocaleDataProvider")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @Tag("BLOCKER")
    void selenideSiteShouldContainAllOfButtonsForGivenLocale(Locale locale, List<String> buttons) {
        open("https://ru.selenide.org");
        $$("#languages a").find(text(locale.name()));
        $$(".main-menu-pages a").shouldHave(texts(buttons));
    }

}
