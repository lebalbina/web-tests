package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WaitHelper helper;

    private final By errorContainerLocator= By.cssSelector("div.error-message-container.error");
    private final By userNameLocator = By.name("user-name");
    private final By passwordLocator = By.name("password");
    private final By loginBtnLocator = By.name("login-button");

    public LoginPage(WebDriver webDriver) {
        driver = webDriver;
        helper = new WaitHelper(driver);
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
        return new HomePage(driver);
    }

    public LoginPage submitLoginFailure() {
        driver.findElement(loginBtnLocator).click();
        return this;
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

