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

    private final String SAUCE_LABS_BACKPACK = "Sauce Labs Backpack";
    private final String SAUCE_LABS_BIKE_LIGHT = "Sauce Labs Bike Light";

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
        homePage.clickProductBtn(SAUCE_LABS_BACKPACK);
        Assert.assertTrue(homePage.isBadgeVisible());
        homePage.clickProductBtn(SAUCE_LABS_BACKPACK);
        Assert.assertFalse(homePage.isBadgeVisible());
    }

    @Test
    @Description("Verifies that shopping cart displays correct number of items")
    @Step("Add two items then remove one")
    @Severity(SeverityLevel.NORMAL)
    public void addTwoItemsThenRemoveOne() {
        homePage = login();
        homePage.clickProductBtn(SAUCE_LABS_BACKPACK);
        homePage.clickProductBtn(SAUCE_LABS_BIKE_LIGHT);
        Assert.assertEquals(homePage.getShoppingCartValue(), 2);
        homePage.clickProductBtn(SAUCE_LABS_BIKE_LIGHT);
        Assert.assertEquals(homePage.getShoppingCartValue(), 1);
    }
}


