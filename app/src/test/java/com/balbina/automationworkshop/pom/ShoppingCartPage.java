package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.EnvironmentConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShoppingCartPage extends BasePage<ShoppingCartPage> {
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By checkoutShoppingBtn = By.id("checkout");

    public ShoppingCartPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void load() {
        driver.get(EnvironmentConfig.getPage("cart.html"));
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, EnvironmentConfig.getPage("cart.html"), "Not on cart page: " + url);
    }

    public CheckoutPageForm clickCheckout() {
        helper.waitForElementToBeVisible(checkoutShoppingBtn, 5).click();
        return new CheckoutPageForm(driver);
    }
}

