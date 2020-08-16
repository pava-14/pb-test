package ru.pb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchWidget {
    private WebDriver driver;
    private String searchButtonLocator = "[action=\"/search\"] button[type=submit]";
    private String inputLocator = "input[id=header-search]";

    public SearchWidget(WebDriver driver) {
        this.driver = driver;
    }

    public void searchFor(String request) {
        driver.findElement(By.cssSelector(inputLocator)).clear();
        driver.findElement(By.cssSelector(inputLocator)).sendKeys(request);
        driver.findElement(By.cssSelector(searchButtonLocator)).click();
    }
}
