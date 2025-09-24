package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HomePage extends LoadableComponent<HomePage> {

    private final WebDriver driver;
    private final WaitHelper helper;
    private static String HOME_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    private final By inventorySortingContainer = By.xpath("//select[@class=\"product_sort_container\"]");
    private final By inventoryItemLocator = By.className("inventory_item");
    private final By cartLocator = By.id("shopping_cart_container");

    public HomePage(WebDriver webDriver) {
        driver = webDriver;
        helper = new WaitHelper(driver);
    }

    @Override
    protected void load() {
        driver.get(HOME_PAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, HOME_PAGE_URL, "Not on home page: " + url);
    }

    public boolean isAt() {
        return Objects.requireNonNull(driver.getCurrentUrl()).equals(HOME_PAGE_URL);
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
}


