package ru.pb.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchWidget {
    private WebDriver driver;
    @FindBy(css = "input[id=header-search]")
    private WebElement requestInput;
    @FindBy(css = "[action=\"/search\"] button[type=submit]")
    private WebElement searchButton;

    public SearchWidget(WebDriver driver) {
        this.driver = driver;
    }

    public void searchFor(String request) {
        requestInput.clear();
        requestInput.sendKeys(request);
        searchButton.click();
    }
}
