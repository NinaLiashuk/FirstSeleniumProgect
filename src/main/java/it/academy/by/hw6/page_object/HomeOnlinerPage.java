package it.academy.by.hw6.page_object;

import it.academy.by.hw6.navigation.OnlinerCatalogNavigation;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class HomeOnlinerPage extends BasePage{

    private static final String MAIN_NAVIGATION_LINK_PATTERN =
            "//*[contains(@class, 'main-navigation__text') and contains(text(), '%s')]";

    public void clickOnMainNavigationLink(String link){
        waitForElementVisible(By.xpath(format(MAIN_NAVIGATION_LINK_PATTERN, link))).click();
    }

    public OnlinerCatalogNavigation clickOnCatalogNavigationLink(){
        waitForElementVisible(By.xpath(format(MAIN_NAVIGATION_LINK_PATTERN, "Каталог"))).click();
        return new OnlinerCatalogNavigation();
    }

    public boolean isHomePageElementDisplayed (String link){
        return isElementDisplayed(By.xpath(format(MAIN_NAVIGATION_LINK_PATTERN, link)));
    }
}
