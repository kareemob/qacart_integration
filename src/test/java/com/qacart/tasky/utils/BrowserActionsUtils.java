package com.qacart.tasky.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public final class BrowserActionsUtils {
    private BrowserActionsUtils() {
    }

    public static Actions actions() {
        return new Actions(getDriver());
    }

    public static void clickButtonJS(By locator) {
        WebElement button = getDriver().findElement(locator);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", button);
    }


    public static void sendKeysWithJS(By locator, String text) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('value', arguments[1]);",
                getDriver().findElement(locator), text);
    }
}
