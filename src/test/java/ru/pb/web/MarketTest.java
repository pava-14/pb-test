package ru.pb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pb.data.Product;
import ru.pb.page.TabletsPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class MarketTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String marketUrl = "https://market.yandex.ru/";
    private final String buttonRegionLocator = "//*[text()='Да, спасибо']";
    private final int productCount = 5;
    private final int productindex = 1;
    private final String brandName = "Samsung";
    private final String sortType = "по цене";

    private void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", ".\\webdriver\\chromedriver.exe");
        if (System.getProperty("os.name").contains("nux")) {
            System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        // Needs for CI, but Market restrict
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Before
    public void loadStartPage() {
        setUpDriver();
        driver.get(marketUrl);
        wait = new WebDriverWait(driver, 20);
        //Select region
        driver.findElement(By.xpath(buttonRegionLocator)).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldFindProductByName() {
        TabletsPage tabletsPage = new TabletsPage(driver, wait);
        tabletsPage.openPage();
        tabletsPage.selectProductsByFilter();
        tabletsPage.writeLog(productCount);
        Product expectedProduct = tabletsPage.getProductByIndex(productindex);
        Product searchedProduct = tabletsPage.firstSearchedProduct(expectedProduct.getName());
        assertEquals(expectedProduct, searchedProduct);
    }
}
