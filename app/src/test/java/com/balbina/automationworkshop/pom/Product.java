package com.balbina.automationworkshop.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {
    private final WebElement root;
    private final By nameLocator = By.className("inventory_item_name");
    private final By priceLocator = By.className("inventory_item_price");
    private final By buttonLocator = By.cssSelector("button");

    public Product(WebElement root) {
        this.root = root;
    }

    public String getName() {
        return root.findElement(nameLocator).getText();
    }

    public Double getPrice() {
        return Double.parseDouble(
                root
                        .findElement(priceLocator)
                        .getText()
                        .replace("$", "")
        );
    }

    public void clickAddOrRemove() {
        root.findElement(buttonLocator).click();
    }
}

