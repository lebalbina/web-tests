package com.balbina.automationworkshop;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

//TODO assert visibility of containers (.isDisplayed())
//TODO constants for error messages
@Epic("Login")
@Feature("Login")
public class LoginTest {
    WebDriver driver;
    WebDriverWait driverWait;
    By errorContainerLocator;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        errorContainerLocator = By.cssSelector("div.error-message-container.error");
    }

    @Test
    @Description("Verifies that entering correct data logs user in")
    @Severity(SeverityLevel.BLOCKER)
    @Step("Enter correct username and password")
    public void loginSuccess() {
        loginAttempt("standard_user", "secret_sauce");

        WebElement inventory = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#inventory_container")));

        Assert.assertTrue(inventory.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    @Description("Verifies that entering wrong password displays error correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Enter wrong password")
    public void loginFailed_wrongPassword() {
        loginAttempt("standard_user", "wrong_password");

        WebElement errorContainer = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(errorContainerLocator)
        );

        Assert.assertTrue(errorContainer.isDisplayed());
        Assert.assertEquals(errorContainer.getText(),
                            "Epic sadface: Username and password do not match any user in this service"
        );
    }

    @Test
    @Description("Verifies that entering no password displays error correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Do not enter password")
    public void loginFailed_noPassword() {
        loginAttempt("standard_user", "");

        WebElement errorContainer = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(errorContainerLocator));

        Assert.assertTrue(errorContainer.isDisplayed());
        Assert.assertEquals(errorContainer.getText(),
                            "Epic sadface: Password is required"
        );
    }

    @Test
    @Description("Verifies that entering no username displays error correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Do not enter username")
    public void loginFailed_noUsername() {
        loginAttempt("", "secret_sauce");

        WebElement errorContainer = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(errorContainerLocator));

        Assert.assertTrue(errorContainer.isDisplayed());
        Assert.assertEquals(errorContainer.getText(),
                            "Epic sadface: Username is required"
        );
    }

    @Test
    @Description("Verifies that entering no data displays error correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Do not enter username and password")
    public void loginFailed_noUsername_noPassword() {
        loginAttempt("", "");

        WebElement errorContainer = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(errorContainerLocator));

        Assert.assertTrue(errorContainer.isDisplayed());
        Assert.assertEquals(errorContainer.getText(),
                            "Epic sadface: Username is required"
        );
    }

    private void loginAttempt(String username, String password) {
        WebElement usernameTxt = driver.findElement(By.name("user-name"));
        WebElement pwdTxt = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.name("login-button"));

        usernameTxt.sendKeys(username);
        pwdTxt.sendKeys(password);
        loginBtn.click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


