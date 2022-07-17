package it.academy.by.hw6.page_object;

import it.academy.by.hw6.WebDriverDiscovery;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {

    private final WebDriver driver;

    private WebDriverDiscovery webDriverDiscovery;

    public BasePage(){
        this.webDriverDiscovery = new WebDriverDiscovery();
        this.driver = webDriverDiscovery.getWebDriver();
    }

    public WebElement waitForElementVisible(By by){
        Wait<WebDriver> wait = new WebDriverWait(driver,10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void navigate(String url){
        driver.get(url);
    }

    public void closeBrowser(){
        driver.quit();
    }

    public boolean isElementDisplayed(By by){
        try {
            waitForElementVisible(by);
            WebElement element = driver.findElement(by);
            return element.isDisplayed() && this.isElementExists(by);
        } catch (Exception e) {
            System.out.println("Element is not exist");
        }
        return false;
    }

    public boolean isElementExists(By by) {
        try {
            List<WebElement> list = driver.findElements(by);
            return !list.isEmpty();
        } catch (Exception e) {
            System.out.println("Element is not exist");
        }
        return false;
    }

    public List<WebElement> getListElements(By by){
        List<WebElement> list = driver.findElements(by);
        return list;
    }
}
