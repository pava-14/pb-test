package ru.pb.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pb.data.Product;
import ru.pb.pages.HomePage;
import ru.pb.pages.TabletsPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class MarketTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String marketUrl = "https://market.yandex.ru/";
    private final int productCount = 5;
    private final int productindex = 1;
    private final String brandName = "Samsung";

    private void setUpDriver() {
        if (System.getProperty("os.name").contains("nux")) {
            System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", ".\\webdriver\\chromedriver.exe");
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
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldFindProductByName() {
        HomePage homePage = new HomePage(driver, wait);
        TabletsPage tabletsPage = homePage.openPageTablets()
                .selectProductsByBrand(brandName)
                .writeToLog(productCount);
        Product expectedProduct = tabletsPage.getProductByIndex(productindex);
        Product searchedProduct = tabletsPage.getFirstSearchedProduct(expectedProduct.getName());
        assertEquals(expectedProduct, searchedProduct);
    }
}
