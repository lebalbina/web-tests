package com.balbina.automationworkshop;

import com.balbina.automationworkshop.pom.HomePage;
import com.balbina.automationworkshop.pom.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected LoginPage loginPage;
    protected HomePage homePage;

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        setUpBrowser(browser);
        loginPage = new LoginPage(getDriver()).get();
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    protected HomePage login() {
        return loginPage
                .typeUsername(System.getenv("CREDS_USR"))
                .typePassword(System.getenv("CREDS_PSW"))
                .submitLoginSuccess();
    }

    private void setUpBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver.set(new ChromeDriver(getChromeOptions()));
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
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
