package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.EnvironmentConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends BasePage<HomePage> {

    private final By inventorySortingContainer = By.className("product_sort_container");
    private final By inventoryItemLocator = By.className("inventory_item");
    private final By cartLocator = By.id("shopping_cart_container");

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void load() {
        driver.get(EnvironmentConfig.getPage("inventory.html"));
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, EnvironmentConfig.getPage("inventory.html"), "Not on home page: " + url);
    }

    public void selectSortingByText(String sorter) {
        Select select = new Select(driver.findElement(inventorySortingContainer));
        select.selectByVisibleText(sorter);
    }

    public List<Product> getProducts() {
        return driver.findElements(inventoryItemLocator)
                .stream()
                .map(Product::new)
                .toList();
    }

    public List<String> getProductNames() {
        return driver.findElements(inventoryItemLocator)
                .stream()
                .map(Product::new)
                .map(Product::getName)
                .toList();
    }

    public List<Double> getProductPrices() {
        return driver.findElements(inventoryItemLocator)
                .stream()
                .map(Product::new)
                .map(Product::getPrice)
                .toList();
    }

    public void clickProductBtn(String name) {
        driver.findElements(inventoryItemLocator)
                .stream()
                .map(Product::new)
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found: " + name))
                .clickAddOrRemove();
    }

    public int getShoppingCartValue() {
        return new ShoppingCart(helper.waitForElementToBeVisible(cartLocator, 5)).getShoppingCartValue();
    }

    public Boolean isBadgeVisible() {
        return new ShoppingCart(helper.waitForElementToBeVisible(cartLocator, 5)).isBadgeVisible();
    }

    public ShoppingCartPage clickShoppingCart() {
        driver.findElement(cartLocator).click();
        return new ShoppingCartPage(driver);
    }
}


