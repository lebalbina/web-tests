package com.balbina.automationworkshop.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {
    private final WebElement root;
    private final By inventoryItemName = By.xpath("//div[@class=\"inventory_item_name \"]");
    private final By inventoryItemPrice = By.className("inventory_item_price");

    public Product(WebElement root) {
        this.root = root;
    }

    public String getName() {
        return root.findElement(inventoryItemName).getText();
    }

    public Double getPrice() {
        return Double.parseDouble(
                root
                        .findElement(inventoryItemPrice)
                        .getText()
                        .replace("$", "")
        );
    }

    //public void addToCart()
}

