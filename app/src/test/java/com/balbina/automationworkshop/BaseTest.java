package com.balbina.automationworkshop;

import com.balbina.automationworkshop.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait driverWait;

    @BeforeMethod
    final public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginAttempt(ConfigReader.getUsername(), ConfigReader.getPassword());
    }

    public void loginAttempt(String username, String password) {
        WebElement usernameTxt = driver.findElement(By.name("user-name"));
        WebElement pwdTxt = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.name("login-button"));

        usernameTxt.sendKeys(username);
        pwdTxt.sendKeys(password);
        loginBtn.click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
