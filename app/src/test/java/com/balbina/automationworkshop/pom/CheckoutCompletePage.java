package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.EnvironmentConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutCompletePage extends BasePage<CheckoutCompletePage> {

    private final By backHome = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get(EnvironmentConfig.getPage("checkout-complete.html"));
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, EnvironmentConfig.getPage("checkout-complete.html"),
                            "Not on checkout complete page: " + url
        );
    }

    public HomePage clickBackHome() {
        helper.waitForElementToBeVisible(backHome, 5).click();
        return new HomePage(driver);
    }
}
