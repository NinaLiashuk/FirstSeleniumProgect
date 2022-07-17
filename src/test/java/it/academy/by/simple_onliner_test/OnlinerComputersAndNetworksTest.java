package it.academy.by.simple_onliner_test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class OnlinerComputersAndNetworksTest {

    static WebDriver driver;
    private static final String DRIVER_PATH = "C:\\Users\\chromedriver_win32\\chromedriver.exe";
    private static final String CATALOG_NAVIGATION_PATH =
            "//*[contains(@class, 'main-navigation__text') and contains(text(), 'Каталог')]";
    private static final String CATALOG_ELEMENTS_PATH = "//*[contains(@class, 'catalog-navigation-classifier')]";
    private static final String COMPUTERS_AND_NETWORKS_ELEMENT_PATH = "//li[@data-id=\"2\"]";

    ////div[contains(@class, 'result') and not(contains(@class, 'grid'))]"
    @BeforeAll
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver",
                DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://catalog.onliner.by");
        WebElement element = driver.findElement(By
                .xpath(CATALOG_NAVIGATION_PATH));
        element.click();
    }

    @BeforeTest
    public void clickOnElementComputersAndNetworks (){
        WebElement element = driver.findElement(By.xpath("//li[@data-id=\"2\"]"));
        element.click();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Электроника", "Компьютеры и сети", "Бытовая техника", "Стройка и ремонт", "Дом и сад",
            "Авто и мото", "Красота и спорт", "Детям и мамам", "Работа и офис"})
    public void testThatCatalogContainsElementsFromValues(String value){
        List<WebElement> elementList = driver.findElements(By
                .xpath("//*[contains(@class, 'catalog-navigation-classifier')]"));
        List<String> list = getStringOfWebElements(elementList);
        assertThat(list).as("elements from sources should exist").containsAnyOf(value);
    }

    @ParameterizedTest
    @CsvSource(value = "Еда")
    public void testThatCatalogHaveNoElementFood(String value) {
        List<WebElement> elementList = driver.findElements(By
                .xpath(CATALOG_ELEMENTS_PATH));
        List<String> list = getStringOfWebElements(elementList);
        Assertions.assertFalse(list.contains(value), "such element should not exists");
    }

    public static List<String> getStringOfWebElements(List<WebElement> elements){
        List<String> list = elements.stream().map(element -> element.getText())
                .collect(Collectors.toList());
        return list;
    }

    @AfterAll
    public static void closeBrowser(){
        driver.close();
    }
}
