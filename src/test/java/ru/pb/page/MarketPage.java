package ru.pb.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MarketPage {
    private WebDriver driver;
    private WebElement linkComputers = driver.findElement(By.xpath("//*[text()='Компьютеры']"));


    public MarketPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean clickComputers () {
        linkComputers.click();
        return driver.findElement((By.xpath("//*[text()='Компьютерная техника'"))).isDisplayed();
    }
}
