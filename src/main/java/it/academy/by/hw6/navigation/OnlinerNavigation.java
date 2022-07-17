package it.academy.by.hw6.navigation;

import it.academy.by.hw6.page_object.HomeOnlinerPage;

public class OnlinerNavigation {

    public static void navigateToOnlinerHomePage(){
        new HomeOnlinerPage().navigate("https://onliner.by");
    }
}
