package com.balbina.automationworkshop;

import com.balbina.automationworkshop.pom.HomePage;
import com.balbina.automationworkshop.pom.LoginPage;
import com.balbina.automationworkshop.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    final public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver).get();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected HomePage login() {
        return loginPage
                .typeUsername(ConfigReader.getUsername())
                .typePassword(ConfigReader.getPassword())
                .submitLoginSuccess();
    }
}
