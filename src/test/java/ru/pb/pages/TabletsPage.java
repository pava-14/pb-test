package ru.pb.pages;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pb.data.Product;

@AllArgsConstructor
public class TabletsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String sortType = "по цене";
    private final String titleListLocator = "[data-autotest-id=product-snippet] [data-zone-name=title]";
    private final String priceListLocator = "[data-autotest-id=product-snippet] [data-zone-name=price]";
    private final String searchHeaderLocator = "div[data-reactroot] h1";
    private final String queryCaption = "Нашли, что искали?";
    private final String xptext = "//*[text()='%s']";

    public TabletsPage selectProductsByBrand(String brandName) {
        driver.findElement(By.xpath(String.format(xptext, brandName))).click();
        wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(By.cssSelector(searchHeaderLocator)), brandName));
        driver.findElement(By.xpath(String.format(xptext, sortType))).click();
        return this;
    }

    public TabletsPage writeToLog(int logCount) {
        int count = logCount;
        int currentCount = driver.findElements(By.cssSelector(titleListLocator)).size();
        if (currentCount < count) {
            count = currentCount;
        }

        for (int index = 0; index < count; index++) {
            System.out.println(
                    driver.findElements(By.cssSelector(titleListLocator)).get(index).getText() + " " +
                            driver.findElements(By.cssSelector(priceListLocator)).get(index).getText());
        }
        return this;
    }

    public Product getProductByIndex(int productIndex) {
        return new Product(
                driver.findElements(By.cssSelector(titleListLocator)).get(productIndex).getText(),
                driver.findElements(By.cssSelector(priceListLocator)).get(productIndex).getText());
    }

    public Product getFirstSearchedProduct(String productNameForSearch) {
        SearchWidget searchWidget = new SearchWidget(driver);
        searchWidget.searchFor(productNameForSearch);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xptext, queryCaption))));
        return new Product(
                driver.findElements(By.cssSelector(titleListLocator)).get(0).getText(),
                driver.findElements(By.cssSelector(priceListLocator)).get(0).getText());
    }
}
