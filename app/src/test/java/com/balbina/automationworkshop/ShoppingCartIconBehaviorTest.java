package com.balbina.automationworkshop;

import com.balbina.automationworkshop.pom.HomePage;
import io.qameta.allure.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Shopping Cart")
@Feature("Shopping Cart Badge")
public class ShoppingCartIconBehaviorTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void clearBrowser() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
    }

    @Test
    @Description("Verifies that shopping cart shows up and hides correctly")
    @Step("add first item to basket and then remove it")
    @Severity(SeverityLevel.NORMAL)
    public void addAndRemoveFromCart() {
        homePage = login();
        homePage.clickProductBtn("Sauce Labs Backpack");
        Assert.assertTrue(homePage.isBadgeVisible());
        homePage.clickProductBtn("Sauce Labs Backpack");
        Assert.assertFalse(homePage.isBadgeVisible());
    }

    @Test
    @Description("Verifies that shopping cart displays correct number of items")
    @Step("Add two items then remove one")
    @Severity(SeverityLevel.NORMAL)
    public void counterIncrDecr() {
        homePage = login();
        homePage.clickProductBtn("Sauce Labs Backpack");
        homePage.clickProductBtn("Sauce Labs Bike Light");
        Assert.assertEquals(homePage.getShoppingCartValue(), 2);
        homePage.clickProductBtn("Sauce Labs Bike Light");
        Assert.assertEquals(homePage.getShoppingCartValue(), 1);
    }
}


