package it.academy.by.hw6.page_object;

import it.academy.by.hw6.navigation.OnlinerNavigation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductPageNavigationTest {
    private static HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage();

    @BeforeAll
    public static void openOnlinerHomePage(){
        OnlinerNavigation.navigateToOnlinerHomePage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Электроника", "Компьютеры и", "Бытовая техника", "Стройка и", "Дом и",
            "Авто и", "Красота и", "Детям и", "Работа и"})
    public void testThatCatalogContainsElementsFromValues(String value){
        boolean elementExist = homeOnlinerPage
                .clickOnCatalogNavigationLink()
                .isCatalogElementDisplayed(value);
        assertThat(elementExist).
                as("catalog category title is not displayed via catalog link navigation").
                isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = "Еда")
    public void testThatCategoryFromValueIsNotDisplayed (String value){
        boolean elementExist = homeOnlinerPage
                .clickOnCatalogNavigationLink()
                .isCatalogElementDisplayed(value);
        assertThat(elementExist)
                .as("catalog category title shouldn't have item from value")
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ноутбуки, компьютеры, мониторы", "Комплектующие", "Сетевое оборудование",
            "Хранение данных"})
    public void testThatComputersAndNetworksContainsElementsFromValues(String value){
        boolean elementExist = homeOnlinerPage
                .clickOnCatalogNavigationLink()
                .selectCatalogClassifierElement("Компьютеры и")
                .isCategoryElementDisplayed(value);
        assertThat(elementExist).
                as("catalog category title is not displayed via catalog link navigation").
                isTrue();
    }

    @Test
    public void testThatAllProductItemsVisibleWithTitleNumbersAndMinPrice(){
        List<WebElement> elementList = homeOnlinerPage
                .clickOnCatalogNavigationLink()
                .selectCatalogClassifierElement("Компьютеры и")
                .selectCategory("Комплектующие")
                .getListWidgetsOfProducts("Комплектующие");

        assertThat(elementList.stream())
                .as("product widget should contains title, numbers of goods and minimal price")
                .allMatch(WebElement::isDisplayed)
                .allMatch(element -> element.getAttribute("textContent").contains("р."))
                .allMatch(element -> element.getAttribute("textContent").contains("товар"))
                .noneMatch(element -> element.getAttribute("innerText").isEmpty());
    }

    @AfterAll
    public static void close(){
        homeOnlinerPage.closeBrowser();
    }
}

