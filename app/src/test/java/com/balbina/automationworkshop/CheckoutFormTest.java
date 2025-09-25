package com.balbina.automationworkshop;

import com.balbina.automationworkshop.pom.CheckoutCompletePage;
import com.balbina.automationworkshop.pom.CheckoutPageForm;
import com.balbina.automationworkshop.pom.CheckoutPageSummary;
import com.balbina.automationworkshop.pom.ShoppingCartPage;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Checkout Feature")
@Feature("Checkout Form")
public class CheckoutFormTest extends BaseTest {

    private static final String ADD_TO_CART_BTN = "//button[@id='add-to-cart-sauce-labs-bike-light']";
    public static final String SHOPPING_CART_ICON = "//div[@id='shopping_cart_container']";
    public static final String CHECKOUT_BUTTON = "//button[@id='checkout']";
    public static final String CONTINUE_BTN = "//input[@id='continue']";
    public static final String ERROR_MSG = "//h3[@data-test='error']";
    public static final String ERROR_FORM_FIELDS = "//input[@class='input_error form_input error']";
    public static final String LAST_NAME_INPUT = "//input[@id='last-name']";
    public static final String POSTAL_CODE_INPUT = "//input[@id='postal-code']";

    @Test
    @Description("Verifies that user goes through checkout process without errors")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Filling out checkout form with correct data")
    public void checkoutSuccessfulPathTest() {
        homePage = login();
        homePage.clickProductBtn("Sauce Labs Backpack");
        ShoppingCartPage shoppingCartPage = homePage.clickShoppingCart();
        CheckoutPageForm checkoutPage = shoppingCartPage.clickCheckout();
        checkoutPage.typeFirstName("Balbina");
        checkoutPage.typeLastName("Pinecone");
        checkoutPage.typePostalCode("66666");
        CheckoutPageSummary checkoutPageSummary = checkoutPage.clickContinue();
        CheckoutCompletePage checkoutCompletePage = checkoutPageSummary.clickFinish();
        homePage = checkoutCompletePage.clickBackHome();
        Assert.assertEquals(homePage.getShoppingCartValue(), 0);
    }

    @Description("Verifies that omitting data for name shows error message correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Omit input field Name and checkout")
    @Test
    public void checkoutFormErrorTest() {
//        addToCartAndCheckout();
//
//        inputType(LAST_NAME_INPUT, "Pinecone");
//        inputType(POSTAL_CODE_INPUT, "66666");
//
//        WebElement continueBtn = driver.findElement(By.xpath(CONTINUE_BTN));
//        continueBtn.click();
//
//        WebElement errorMessage = driverWait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MSG)));
//
//        List<WebElement> errorInputEdtList = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                By.xpath(ERROR_FORM_FIELDS))
//        );
//
//        Assert.assertEquals(errorMessage.getText(), "Error: First Name is required");
//        Assert.assertEquals(errorInputEdtList.size(), 3);
//    }
//
//    private void addToCartAndCheckout() {
//        driver.findElement(By.xpath(ADD_TO_CART_BTN)).click();
//        driver.findElement(By.xpath(SHOPPING_CART_ICON)).click();
//        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CHECKOUT_BUTTON))).click();
//    }
//
//    private void inputType(String xpath, String keys) {
//        WebElement edtText = driverWait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
//        );
//        edtText.sendKeys(keys);
    }
}

