package ru.pb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String xptext = "//*[text()='%s']";
    private final String computersPageCaption = "Компьютерная техника";
    private final String linkComputersText = "Компьютеры";
    private final String linkTabletsText = "Планшеты";
    private final String buttonRegionLocator = "//*[text()='Да, спасибо']";

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public TabletsPage openPageTablets() {
        //Select region
        driver.findElement(By.xpath(buttonRegionLocator)).click();
        driver.findElement(By.xpath(String.format(xptext, linkComputersText))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xptext, computersPageCaption))));
        driver.findElement(By.xpath(String.format(xptext, linkTabletsText))).click();
        return new TabletsPage(driver, wait);
    }
}
