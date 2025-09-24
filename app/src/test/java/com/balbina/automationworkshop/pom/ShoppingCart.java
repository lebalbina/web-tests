package com.balbina.automationworkshop.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ShoppingCart {
    private final WebElement root;
    private final By badgeLocator = By.className("shopping_cart_badge");

    public ShoppingCart(WebElement root) {
        this.root = root;
    }

    public int getShoppingCartValue() {
        try {
            WebElement element = root.findElement(badgeLocator);
            return Integer.parseInt(element.getText());
        }
        catch (NoSuchElementException e) {
            return 0;
        }
    }

    public Boolean isBadgeVisible() {
        try {
            root.findElement(badgeLocator);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
}
