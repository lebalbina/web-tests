package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

public class HomePage extends LoadableComponent<HomePage> {

    private final WebDriver driver;
    private final WaitHelper helper;
    private static String HOME_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    private final By inventorySortingContainer = By.xpath("//select[@class=\"product_sort_container\"]");
    private final By inventoryItemLocator = By.xpath("//div[@class=\"inventory_item\"]");


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
}


