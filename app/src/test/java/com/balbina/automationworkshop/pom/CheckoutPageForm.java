package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.EnvironmentConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Objects;

public class CheckoutPageForm extends BasePage<CheckoutPageForm> {
    private final By fistNameLocator = By.id("first-name");
    private final By lastNameLocator = By.id("last-name");
    private final By zipLocator = By.id("postal-code");
    private final By continueLocator = By.id("continue");
    private final By errorContainer = By.cssSelector(".error-message-container.error");

    public CheckoutPageForm(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get(EnvironmentConfig.getPage("checkout-step-one.html"));
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, EnvironmentConfig.getPage("step-one.html"),
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

    public CheckoutPageSummary clickContinueExpectSuccess() {
        helper.waitForElementToBeVisible(continueLocator, 5).click();
        return new CheckoutPageSummary(driver).get();
    }

    public void clickContinueExpectFailure() {
        helper.waitForElementToBeVisible(continueLocator, 5).click();
    }

    public String getErrorMsg() {
        return new ErrorBtn(helper.waitForElementToBeVisible(errorContainer, 5)).getErrorMsg();
    }
}
