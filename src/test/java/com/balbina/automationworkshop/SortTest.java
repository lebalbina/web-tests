package com.balbina.automationworkshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest extends BaseTest {

    @Test
    public void sortAlphabeticallyAsc() {
        WebElement sortingContainer = driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]"));
        sortingContainer.click();

        Select select = new Select(sortingContainer);
        select.selectByVisibleText("Name (A to Z)");

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"inventory_item\"]")));

        List<WebElement> itemDescriptions = driver.findElements(By.xpath("//div[@class=\"inventory_item_name \"]"));
        List<String> itemNames = new ArrayList<>();
        for (WebElement webElement : itemDescriptions) {
            itemNames.add(webElement.getText());
        }

        List<String> expected = new ArrayList<>(itemNames);
        Collections.sort(expected);

        Assert.assertEquals(itemNames, expected);
    }

    @Test
    public void sortAlphabeticallyDesc() {
        WebElement sortingContainer = driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]"));
        sortingContainer.click();

        Select select = new Select(sortingContainer);
        select.selectByVisibleText("Name (Z to A)");

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"inventory_item\"]")));

        List<WebElement> itemDescriptions = driver.findElements(By.xpath("//div[@class=\"inventory_item_name \"]"));
        List<String> itemNames = new ArrayList<>();
        for (WebElement webElement : itemDescriptions) {
            itemNames.add(webElement.getText());
        }

        List<String> expected = new ArrayList<>(itemNames);
        expected.sort(Comparator.reverseOrder());

        Assert.assertEquals(itemNames, expected);
    }

    @Test
    public void sortPriceAscending() {
        WebElement filterBtn = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container"))
        );
        filterBtn.click();

        Select dropdown = new Select(filterBtn);
        dropdown.selectByVisibleText("Price (low to high)");

        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item")));

        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

        List<Double> prices = new ArrayList<>();
        for (WebElement webElement : priceElements) {
            String priceText = webElement.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }

        List<Double> expected = new ArrayList<>(prices);
        Collections.sort(expected);

        Assert.assertEquals(expected, prices);
    }

    @Test
    public void sortPriceDescending() {
        Select select = new Select(driver.findElement(By.className("product_sort_container")));
        select.selectByVisibleText("Price (high to low)");

        List<WebElement> priceWebElements = driverWait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_price"))
        );

        List<Double> prices = new ArrayList<>();
        for (WebElement element : priceWebElements) {
            String price = element.getText().replace("$", "");
            prices.add(Double.parseDouble(price));
        }

        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(prices, sortedPrices);
    }
}

