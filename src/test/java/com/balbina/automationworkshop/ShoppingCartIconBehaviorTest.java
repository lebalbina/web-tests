package com.balbina.automationworkshop;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Shopping Cart")
@Feature("Shopping Cart Badge")
public class ShoppingCartIconBehaviorTest extends BaseTest {

    @Test
    @Description("Verifies that shopping cart gets updated after adding first item in the basket")
    @Step("add first item to basket and then remove it")
    @Severity(SeverityLevel.NORMAL)
    public void addAndRemoveFromCart() {
        WebElement addToCartBtn = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name(("add-to-cart-sauce-labs-backpack"))));
        addToCartBtn.click();

        WebElement shoppingCartContainer = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_container"))
        );
        shoppingCartContainer.click();

        WebElement basketWithOneItem = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
        );

        Assert.assertTrue(basketWithOneItem.isDisplayed());

        WebElement removeBtn = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("remove-sauce-labs-backpack"))
        );
        removeBtn.click();

        boolean isBasketBadgeGone = driverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge"))
        );
        Assert.assertTrue(isBasketBadgeGone);
    }

    @Test
    @Description("Verifies that shopping cart displays correct number of items")
    @Step("Add two items then remove one")
    @Severity(SeverityLevel.NORMAL)
    public void counterIncrDecr() {
        WebElement addToCartBtn1 = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        WebElement addToCartBtn2 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));

        addToCartBtn1.click();
        addToCartBtn2.click();

        WebElement badgeIncr = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
        );

        Assert.assertEquals(badgeIncr.getText(), "2");

        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
        removeBtn.click();

        WebElement badgeDecr = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
        );

        Assert.assertEquals(badgeDecr.getText(), "1");
    }
}

