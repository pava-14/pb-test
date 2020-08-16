package ru.pb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pb.data.Product;

import java.util.List;

public class TabletsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String brandName = "Samsung";
    private final String sortType = "по цене";
//    private final String linkComputersText = "Компьютеры";
//    private final String linkTabletsText = "Планшеты";
    @FindBy(xpath = "//*[text()='Компьютеры']")
    public WebElement linksComputers;
    @FindBy(xpath = "//*[text()='Планшеты']")
    private WebElement linkTablets;
//    @FindBy(xpath = "//*[text()='\" + linkComputersText + \"']")
//    private WebElement linksComputers;
//    @FindBy(xpath = "//*[text()='\" + linkTabletsText + \"']")
//    private WebElement linkTablets;
    @FindBy(css = "div[data-reactroot] h1")
    private WebElement searchHeader;
    @FindBy(xpath = "//*[text()='\" + brandName + \"']")
    private WebElement checkboxBrand;
    @FindBy(xpath = "//*[text()='\" + sortType + \"']")
    private WebElement checkboxPrice;
    @FindBy(css = "[data-autotest-id=product-snippet] [data-zone-name=title]")
    private List<WebElement> titleList;
    @FindBy(css = "[data-autotest-id=product-snippet] [data-zone-name=price]")
    private List<WebElement> priceList;
    @FindBy(css = "title")
    private WebElement pageTitle;

    public TabletsPage(final WebDriver driver, final WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openPage() {
        linksComputers.click();
        linkTablets.click();
    }

    public void selectProducts() {
        checkboxBrand.click();
        checkboxPrice.click();
        wait.until(ExpectedConditions.textToBePresentInElement(searchHeader, brandName));
    }

    public void writeLog (int logCount){
        int count = logCount;
        if (titleList.size() < count) {
            count = titleList.size();
        }
        int index = 0;
        while (index < count) {
            System.out.println(titleList.get(index).getText() + " " + priceList.get(index).getText());
            index++;
        }
    }

    public Product getProduct(int productIndex) {
        return new Product(titleList.get(productIndex).getText(), priceList.get(productIndex).getText());
    }

    public void searchProduct (String productName) {
        SearchWidget searchWidget = new SearchWidget(driver);
        searchWidget.searchFor(productName);
        wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, productName));
    }
}
