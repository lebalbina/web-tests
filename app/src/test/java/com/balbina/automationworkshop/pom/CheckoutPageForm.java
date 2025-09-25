package com.balbina.automationworkshop.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Objects;

public class CheckoutPageForm extends BasePage<CheckoutPageForm> {
    private final By fistNameLocator = By.id("first-name");
    private final By lastNameLocator = By.id("last-name");
    private final By zipLocator = By.id("postal-code");
    private final By continueLocator = By.id("continue");

    public CheckoutPageForm(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(Objects.requireNonNull(url).endsWith("step-one.html"),
                          "Not on checkout form page: " + url
        );
    }

    public void typeFirstName(String input) {
        helper.waitForElementToBeVisible(fistNameLocator, 5).sendKeys(input);
    }

    public void typeLastName(String input) {
        helper.waitForElementToBeVisible(lastNameLocator, 5).sendKeys(input);
    }

    public void typePostalCode(String input) {
        helper.waitForElementToBeVisible(zipLocator, 5).sendKeys(input);
    }

    public CheckoutPageSummary clickContinue() {
        helper.waitForElementToBeVisible(continueLocator, 5).click();
        return new CheckoutPageSummary(driver);
    }
}
