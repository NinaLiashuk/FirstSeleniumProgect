package it.academy.by.hw6.navigation;

import it.academy.by.hw6.page_object.HomeOnlinerPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class OnlinerCatalogNavigation extends HomeOnlinerPage {

    private static final String CATALOG_GORIZONTAL_LIST_NAVIGATION_PATTERN =
            "//span[contains(@class, 'catalog-navigation-classifier') and contains(text(), '%s')]";

    private static final String CATEGORY_VERTICAL_LIST_NAVIGATION_PATTERN =
            "//div[contains(@class,'catalog-navigation-list__aside-item') and .//*[contains(text(), '%s')]]";

    private static final String PRODUCT_LIST_WIDGETS_NAVIGATION_PATTERN =
            "//*[@class=\"catalog-navigation-list__aside-title\" and normalize-space(text())=\"%s\"]" +
                    "/parent::*//*[@class=\"catalog-navigation-list__dropdown-data\"]";


    public OnlinerCatalogNavigation selectCatalogClassifierElement(String catalogElement){
        waitForElementVisible(xpath(format(CATALOG_GORIZONTAL_LIST_NAVIGATION_PATTERN, catalogElement))).click();
        return this;
    }

    public OnlinerCatalogNavigation selectCategory (String category){
        waitForElementVisible(xpath(format(CATEGORY_VERTICAL_LIST_NAVIGATION_PATTERN, category))).click();
        return this;
    }

    public boolean isCatalogElementDisplayed(String catalogElement){
        return isElementDisplayed(xpath(format(CATALOG_GORIZONTAL_LIST_NAVIGATION_PATTERN, catalogElement)));
    }

    public boolean isCategoryElementDisplayed(String category){
        return isElementDisplayed(xpath(format(CATEGORY_VERTICAL_LIST_NAVIGATION_PATTERN, category)));
    }

    /**
     * Метод получает виджеты-список товаров после выбора категории из вертикального списка
     * @param category - выбранная категория товаров
     * @return лист объектов класса WebElement
     */
    public List<WebElement> getListWidgetsOfProducts(String category){
        List<WebElement> list = getListElements(xpath(format(PRODUCT_LIST_WIDGETS_NAVIGATION_PATTERN, category)));
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getText());
        }
        System.out.println(list.size());
        return list;
    }
}
