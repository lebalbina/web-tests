package com.balbina.automationworkshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutFormTest extends BaseTest {

    private static final String ADD_TO_CART_BTN = "//button[@id='add-to-cart-sauce-labs-bike-light']";
    public static final String SHOPPING_CART_ICON = "//div[@id='shopping_cart_container']";
    public static final String CHECKOUT_BUTTON = "//button[@id='checkout']";
    public static final String CONTINUE_BTN = "//input[@id='continue']";
    public static final String FINISH_BTN = "//button[@id='finish']";
    public static final String SHOPPING_CART_BADGE = "//span[@class='shopping_cart_badge']";
    public static final String ERROR_MSG = "//h3[@data-test='error']";
    public static final String ERROR_FORM_FIELDS = "//input[@class='input_error form_input error']";
    public static final String FIRST_NAME_INPUT = "//input[@id='first-name']";
    public static final String LAST_NAME_INPUT = "//input[@id='last-name']";
    public static final String POSTAL_CODE_INPUT = "//input[@id='postal-code']";

    @Test
    public void checkoutSuccessfulPathTest() {
        addToCartAndCheckout();

        inputType(FIRST_NAME_INPUT, "Balbina");
        inputType(LAST_NAME_INPUT, "Pinecone");
        inputType(POSTAL_CODE_INPUT, "66666");

        WebElement continueBtn = driver.findElement(By.xpath(CONTINUE_BTN));
        continueBtn.click();

        WebElement finishBtn = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(FINISH_BTN)));
        finishBtn.click();

        String currentUrl = driver.getCurrentUrl();
        List<WebElement> shoppingCartBadge = driver.findElements(By.xpath(SHOPPING_CART_BADGE));

        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(shoppingCartBadge.isEmpty());
    }

    @Test
    public void checkoutFormErrorTest() {
        addToCartAndCheckout();

        inputType(LAST_NAME_INPUT, "Pinecone");
        inputType(POSTAL_CODE_INPUT, "66666");

        WebElement continueBtn = driver.findElement(By.xpath(CONTINUE_BTN));
        continueBtn.click();

        WebElement errorMessage = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MSG)));

        List<WebElement> errorInputEdtList = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(ERROR_FORM_FIELDS))
        );

        Assert.assertEquals(errorMessage.getText(), "Error: First Name is required");
        Assert.assertEquals(errorInputEdtList.size(), 3);
    }

    private void addToCartAndCheckout() {
        driver.findElement(By.xpath(ADD_TO_CART_BTN)).click();
        driver.findElement(By.xpath(SHOPPING_CART_ICON)).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CHECKOUT_BUTTON))).click();
    }

    private void inputType(String xpath, String keys) {
        WebElement edtText = driverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
        );
        edtText.sendKeys(keys);
    }
}

