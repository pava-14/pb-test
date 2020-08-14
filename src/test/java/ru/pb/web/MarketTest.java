package ru.pb.web;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.*;

import java.util.List;

public class MarketTest {
    private WebDriver driver;
    private final String marketUrl = "https://market.yandex.ru/";

    void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", ".\\webdriver\\chromedriver.exe");
        if (System.getProperty("os.name").contains("nux")) {
            System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldFindProductByName() {
        setUpDriver();
        driver.get(marketUrl);
//        String title = driver.getTitle();
//        WebElement tablist = driver.findElement(By.cssSelector("[role=tablist]"));
        driver.findElement(By.xpath("//*[text()='Да, спасибо']")).click();
        driver.findElement(By.xpath("//*[text()='Компьютеры']")).click();
        assertTrue(driver.findElement(By.xpath("//*[text()='Компьютерная техника']")).isDisplayed());
        driver.findElement(By.xpath("//*[text()='Планшеты']")).click();
        driver.findElement(By.xpath("//*[text()='Samsung']")).click();
        driver.findElement(By.xpath("//*[text()='по цене']")).click();

        List<WebElement> productList = driver.findElements(By.cssSelector("[data-autotest-id=product-snippet]"));
        WebElement product = productList.get(0);
        product.findElement(By.cssSelector([data-zone-name=title]))

        tearDown();
    }
}
