package com.balbina.automationworkshop;

import com.balbina.automationworkshop.pom.HomePage;
import com.balbina.automationworkshop.pom.LoginPage;
import com.balbina.automationworkshop.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//TODO constants for error messages
//TODO failing logins candidate for parameterized tests
@Epic("Login")
@Feature("Login")
public class LoginTest extends BaseTest {
    private static final String STANDARD_USER = "standard_user";
    private static final String WRONG_PASSWORD = "wrong_password";

    private LoginPage loginPage;

    @BeforeMethod
    private void initialize() {
        loginPage = new LoginPage(driver).get();
    }

    @Test
    @Description("Verifies that entering correct data logs user in")
    @Severity(SeverityLevel.BLOCKER)
    @Step("Enter correct username and password")
    public void loginSuccess() {
        HomePage homepage = loginPage
                .typeUsername(ConfigReader.getUsername())
                .typePassword(ConfigReader.getPassword())
                .submitLoginSuccess();
        Assert.assertTrue(homepage.isAt());
    }

    @Test
    @Description("Verifies that entering wrong password displays error correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Enter wrong password")
    public void loginFailed_wrongPassword() {
        loginPage = loginPage
                .typeUsername(STANDARD_USER)
                .typePassword(WRONG_PASSWORD)
                .submitLoginFailure();

        Assert.assertTrue(loginPage.errorContainerIsDisplayed());
        Assert.assertEquals(loginPage.getErrorContainerText(),
                            "Epic sadface: Username and password do not match any user in this service"
        );
    }

    @Test
    @Description("Verifies that entering no password displays error correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Do not enter password")
    public void loginFailed_noPassword() {
        loginPage = loginPage
                .typeUsername(STANDARD_USER)
                .submitLoginFailure();

        Assert.assertTrue(loginPage.errorContainerIsDisplayed());
        Assert.assertEquals(loginPage.getErrorContainerText(),
                            "Epic sadface: Password is required"
        );
    }

    @Test
    @Description("Verifies that entering no username displays error correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Do not enter username")
    public void loginFailed_noUsername() {
        loginPage = loginPage
                .typePassword(WRONG_PASSWORD)
                .submitLoginFailure();

        Assert.assertTrue(loginPage.errorContainerIsDisplayed());
        Assert.assertEquals(loginPage.getErrorContainerText(),
                            "Epic sadface: Username is required"
        );
    }

    @Test
    @Description("Verifies that entering no data displays error correctly")
    @Severity(SeverityLevel.NORMAL)
    @Step("Do not enter username and password")
    public void loginFailed_noUsername_noPassword() {
        loginPage = loginPage.submitLoginFailure();

        Assert.assertTrue(loginPage.errorContainerIsDisplayed());
        Assert.assertEquals(loginPage.getErrorContainerText(),
                            "Epic sadface: Username is required"
        );
    }
}
