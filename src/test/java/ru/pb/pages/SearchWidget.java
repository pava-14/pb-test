package ru.pb.pages;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class SearchWidget {
    private WebDriver driver;
    private final String searchButtonLocator = "[action=\"/search\"] button[type=submit]";
    private final String inputLocator = "input[id=header-search]";

    public void searchFor(String searchRequest) {
        driver.findElement(By.cssSelector(inputLocator)).clear();
        driver.findElement(By.cssSelector(inputLocator)).sendKeys(searchRequest);
        driver.findElement(By.cssSelector(searchButtonLocator)).click();
    }
}
