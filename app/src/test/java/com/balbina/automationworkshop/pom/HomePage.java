package com.balbina.automationworkshop.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class HomePage {
    private final WebDriver driver;

    public static String HOME_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    private final By inventoryContainerLocator = By.cssSelector("div#inventory_container");

    public HomePage(WebDriver webDriver) {
        driver = webDriver;
    }

    public boolean isAt() {
        return Objects.requireNonNull(driver.getCurrentUrl()).equals(HOME_PAGE_URL);
    }
}
