package com.balbina.automationworkshop.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {
    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToBeVisible(By locator, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
