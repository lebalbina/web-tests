package com.balbina.automationworkshop.pom;

import com.balbina.automationworkshop.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage<T extends BasePage<T>> extends LoadableComponent<T> {
    protected WebDriver driver;
    protected WaitHelper helper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        helper = new WaitHelper(driver);
    }
}
