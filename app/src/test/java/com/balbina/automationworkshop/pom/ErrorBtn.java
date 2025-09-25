package com.balbina.automationworkshop.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ErrorBtn {
    private final WebElement root;
    private final By errorBtnLocator = By.cssSelector("[data-test='error']");

    public ErrorBtn(WebElement root) {
        this.root = root;
    }

    public String getErrorMsg() {
        return root.findElement(errorBtnLocator).getText();
    }

    public Boolean isVisible() {
        return root.isDisplayed();
    }
}
