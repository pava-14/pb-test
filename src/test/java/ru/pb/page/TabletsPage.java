package ru.pb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pb.data.Product;

public class TabletsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String brandName = "Samsung";
    private final String sortType = "по цене";
    private final String linkComputersText = "Компьютеры";
    private final String linkTabletsText = "Планшеты";
    private String titleListLocator = "[data-autotest-id=product-snippet] [data-zone-name=title]";
    private String priceListLocator = "[data-autotest-id=product-snippet] [data-zone-name=price]";
    private String computersPageLocator = "//*[text()='Компьютерная техника']";
    private String searchHeaderLocator = "div[data-reactroot] h1";
    private String queryCaption = "Нашли, что искали?";

    public TabletsPage(final WebDriver driver, final WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public TabletsPage openPage() {
        driver.findElement(By.xpath("//*[text()='" + linkComputersText + "']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(computersPageLocator)));
        driver.findElement(By.xpath("//*[text()='" + linkTabletsText + "']")).click();
        return this;
    }

    public TabletsPage selectProductsByFilter() {
        driver.findElement(By.xpath("//*[text()='" + brandName + "']")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(By.cssSelector(searchHeaderLocator)), brandName));
        driver.findElement(By.xpath("//*[text()='" + sortType + "']")).click();
        return this;
    }

    public TabletsPage writeLog(int logCount) {
        int count = logCount;
        int currentCount = driver.findElements(By.cssSelector(titleListLocator)).size();
        if (currentCount < count) {
            count = currentCount;
        }
        int index = 0;
        while (index < count) {
            System.out.println(
                    driver.findElements(By.cssSelector(titleListLocator)).get(index).getText() + " " +
                            driver.findElements(By.cssSelector(priceListLocator)).get(index).getText());
            index++;
        }
        return this;
    }

    public Product getProductByIndex(int productIndex) {
        return new Product(
                driver.findElements(By.cssSelector(titleListLocator)).get(productIndex).getText(),
                driver.findElements(By.cssSelector(priceListLocator)).get(productIndex).getText());
    }

    public Product firstSearchedProduct(String productNameForSearch) {
        SearchWidget searchWidget = new SearchWidget(driver);
        searchWidget.searchFor(productNameForSearch);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='" + queryCaption + "']")));
        return new Product(
                driver.findElements(By.cssSelector(titleListLocator)).get(0).getText(),
                driver.findElements(By.cssSelector(priceListLocator)).get(0).getText());
    }
}
