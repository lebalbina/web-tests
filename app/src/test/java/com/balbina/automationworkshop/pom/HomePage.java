package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomePage extends LoadableComponent<HomePage> {

    private final WebDriver driver;
    private final WaitHelper helper;
    private static String HOME_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    private final By inventorySortingContainer = By.xpath("//select[@class=\"product_sort_container\"]");
    private final By inventoryItemLocator = By.xpath("//div[@class=\"inventory_item\"]");
    private final By inventoryItemName = By.xpath("//div[@class=\"inventory_item_name \"]");
    private final By inventoryItemPrice = By.className("inventory_item_price");


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

    public List<String> getItemNames() {
        helper.waitForElementToBeVisible(inventoryItemLocator, 10);
        List<WebElement> itemNamesWebElements = driver.findElements(inventoryItemName);
        List<String> itemNames = new ArrayList<>();
        for (WebElement webElement : itemNamesWebElements) {
            itemNames.add(webElement.getText());
        }
        return itemNames;
    }

    public List<Double> getProductPrices() {
        helper.waitForElementToBeVisible(inventoryItemLocator, 10);
        List<WebElement> priceElements = driver.findElements(inventoryItemPrice);
        List<Double> prices = new ArrayList<>();
        for (WebElement webElement : priceElements) {
            String priceText = webElement.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }


}
