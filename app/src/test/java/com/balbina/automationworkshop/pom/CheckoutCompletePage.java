package com.balbina.automationworkshop.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Objects;

public class CheckoutCompletePage extends BasePage<CheckoutCompletePage> {

    private final By backHome = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get("https://www.saucedemo.com/checkout-complete.html");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(Objects.requireNonNull(url).endsWith("checkout-complete.html.html"),
                          "Not on checkout complete page: " + url
        );
    }

    public HomePage clickBackHome() {
        helper.waitForElementToBeVisible(backHome, 5).click();
        return new HomePage(driver);
    }
}
