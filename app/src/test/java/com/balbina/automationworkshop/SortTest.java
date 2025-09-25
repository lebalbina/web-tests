package com.balbina.automationworkshop;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Epic("Products display")
@Feature("Sorting")
public class SortTest extends BaseTest {

    @Test
    @Description("Verifies that sorting in ascending alphabetical order works")
    @Step("Click sorting A-Z from the dropdown menu")
    @Severity(SeverityLevel.NORMAL)
    public void sortAlphabeticallyAsc() {
        homePage = login();
        homePage.selectSortingByText("Name (A to Z)");
        List<String> itemNames = homePage.getProductNames();
        List<String> expected = new ArrayList<>(itemNames);
        Collections.sort(expected);
        Assert.assertEquals(itemNames, expected);
    }

    @Test
    @Description("Verifies that sorting in descending alphabetical order works")
    @Step("Click sorting Z-A from the dropdown menu")
    @Severity(SeverityLevel.NORMAL)
    public void sortAlphabeticallyDesc() {
        homePage = login();
        homePage.selectSortingByText("Name (Z to A)");
        List<String> itemNames = homePage.getProductNames();
        List<String> expected = new ArrayList<>(itemNames);
        expected.sort(Comparator.reverseOrder());
        Assert.assertEquals(itemNames, expected);
    }

    @Test
    @Description("Verifies that sorting in ascending price order works")
    @Step("Click sorting Price (low to high) from the dropdown menu")
    @Severity(SeverityLevel.NORMAL)
    public void sortPriceAscending() {
        homePage = login();
        homePage.selectSortingByText("Price (low to high)");
        List<Double> prices = homePage.getProductPrices();
        List<Double> expected = new ArrayList<>(prices);
        Collections.sort(expected);
        Assert.assertEquals(prices, expected);
    }

    @Test
    @Description("Verifies that sorting in descending price order works")
    @Step("Click sorting Price (high to low) from the dropdown menu")
    @Severity(SeverityLevel.NORMAL)
    public void sortPriceDescending() {
        homePage = login();
        homePage.selectSortingByText("Price (high to low)");
        List<Double> prices = homePage.getProductPrices();
        List<Double> expected = new ArrayList<>(prices);
        expected.sort(Collections.reverseOrder());
        Assert.assertEquals(prices, expected);
    }
}
