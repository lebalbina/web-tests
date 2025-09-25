package com.balbina.automationworkshop;

import com.balbina.automationworkshop.pom.HomePage;
import com.balbina.automationworkshop.pom.LoginPage;
import com.balbina.automationworkshop.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(getChromeOptions());
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

    private ChromeOptions getChromeOptions() {
        return new ChromeOptions().setExperimentalOption("prefs", getChromePreferences());
    }

    private Map<String, Object> getChromePreferences() {
        Map<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.password_manager_leak_detection", false);
        return chromePreferences;
    }
}
