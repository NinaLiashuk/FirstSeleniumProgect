package it.academy.by.hw6.page_object;

import it.academy.by.hw6.navigation.OnlinerNavigation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;


class HomeOnlinerPageTest {

    private static HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage();

    @BeforeAll
    public static void openOnlinerHomePage(){
        OnlinerNavigation.navigateToOnlinerHomePage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Каталог", "Новости", "Автобарахолка", "Услуги"})
    public void testThatMainOnlinerPageHasCategoriesFromValue (String value) {
        boolean isCatalogExist = homeOnlinerPage
                .isHomePageElementDisplayed(value);
        assertThat(isCatalogExist)
                .as("main category title is not displayed via main link navigation")
                .isTrue();
    }

    @AfterAll
    public static void close(){
        homeOnlinerPage.closeBrowser();
    }
}