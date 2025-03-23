package com.qacart.tasky.driver.managers;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private DriverManager(){}

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void setDriver(WebDriver receivedDriver) {
        driver.set(receivedDriver);
    }
}
