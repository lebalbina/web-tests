package com.balbina.automationworkshop.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BasePage<LoginPage> {

    private final By errorContainerLocator= By.cssSelector("div.error-message-container.error");
    private final By userNameLocator = By.name("user-name");
    private final By passwordLocator = By.name("password");
    private final By loginBtnLocator = By.name("login-button");

    private static String LOGIN_URL = "https://www.saucedemo.com/";

    @Override
    protected void load() {
        driver.get("https://www.saucedemo.com/");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL, "Not on the login page: " + url);
    }

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(userNameLocator).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public HomePage submitLoginSuccess() {
        driver.findElement(loginBtnLocator).click();
        return new HomePage(driver).get();
    }

    public LoginPage submitLoginFailure() {
        driver.findElement(loginBtnLocator).click();
        return new LoginPage(driver);
    }

    public String getErrorContainerText() {
        WebElement element = helper.waitForElementToBeVisible(errorContainerLocator, 10);
        return element.getText();
    }

    public boolean errorContainerIsDisplayed() {
        WebElement element = helper.waitForElementToBeVisible(errorContainerLocator, 10);
        return element.isDisplayed();
    }
}
