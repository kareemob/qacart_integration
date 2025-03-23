package com.qacart.tasky.driver.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxManager {
    private FirefoxManager(){}

    public static WebDriver getFireFoxDriver(){
        FirefoxOptions options = new FirefoxOptions();
        return new FirefoxDriver(options);
    }
}
