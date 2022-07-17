package it.academy.by.hw6.page_object;

import org.openqa.selenium.By;

import static java.lang.String.format;

public class ProductPage extends BasePage{
    private final static String PRODUCT_FROM_WIDGET_LIST_NAVIGATION =
            "//a[@class=\"catalog-navigation-list__dropdown-item\"]//span[contains(@class, 'title') " +
                    "and contains(text(), '%s')]";

    public boolean isProductDisplayed(String product){
        return isElementDisplayed(By.xpath(format(PRODUCT_FROM_WIDGET_LIST_NAVIGATION, product)));
    }
}
