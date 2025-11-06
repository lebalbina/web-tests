package com.balbina.automationworkshop;

import com.balbina.automationworkshop.pom.CheckoutCompletePage;
import com.balbina.automationworkshop.pom.CheckoutPageForm;
import com.balbina.automationworkshop.pom.CheckoutPageSummary;
import com.balbina.automationworkshop.pom.ShoppingCartPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Checkout Feature")
@Feature("Checkout Form")
public class CheckoutFormTest extends BaseTest {

    private final String FIRST_NAME_REQUIRED_ERROR = "Error: First Name is required";
    private final String FIRST_NAME = "Foo";
    private final String LAST_NAME = "Bar";
    private final String POSTAL_CODE = "66666";

    @Test
    @Description("Verifies that user goes through checkout process without errors")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Filling out checkout form with correct data")
    public void checkoutSuccessfulPathTest() {
        homePage = login();
        homePage.clickProductBtn("Sauce Labs Backpack");
        ShoppingCartPage shoppingCartPage = homePage.clickShoppingCart();
        CheckoutPageForm checkoutPageForm = shoppingCartPage.clickCheckout();
        checkoutPageForm.typeFirstName(FIRST_NAME);
        checkoutPageForm.typeLastName(LAST_NAME);
        checkoutPageForm.typePostalCode(POSTAL_CODE);
        CheckoutPageSummary checkoutPageSummary = checkoutPageForm.clickContinueExpectSuccess();
        CheckoutCompletePage checkoutCompletePage = checkoutPageSummary.clickFinish();
        homePage = checkoutCompletePage.clickBackHome();
        Assert.assertEquals(homePage.getShoppingCartValue(), 0);
    }

    @Description("Verifies that omitting data for name shows error message correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Omit input field Name and checkout")
    @Test
    public void checkoutFormErrorTest() {
        homePage = login();
        homePage.clickProductBtn("Sauce Labs Backpack");
        ShoppingCartPage shoppingCartPage = homePage.clickShoppingCart();
        CheckoutPageForm checkoutPageForm = shoppingCartPage.clickCheckout();
        checkoutPageForm.typeLastName(LAST_NAME);
        checkoutPageForm.typePostalCode(POSTAL_CODE);
        checkoutPageForm.clickContinueExpectFailure();
        Assert.assertEquals(checkoutPageForm.getErrorMsg(), FIRST_NAME_REQUIRED_ERROR);
    }
}


