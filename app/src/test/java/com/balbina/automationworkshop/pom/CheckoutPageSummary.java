package com.balbina.automationworkshop.pom;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Objects;

public class CheckoutPageSummary extends BasePage<CheckoutPageSummary> {

    private final By finishLocator = By.id("finish");

    public CheckoutPageSummary(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(Objects.requireNonNull(url).endsWith("step-two.html"),
                          "Not on checkout summary page: " + url
        );
    }

    public CheckoutCompletePage clickFinish() {
        helper.waitForElementToBeVisible(finishLocator, 5).click();
        return new CheckoutCompletePage(driver);
    }
}
