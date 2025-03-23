package com.qacart.tasky.driver;

import com.qacart.tasky.driver.factory.DriverFactory;
import com.qacart.tasky.driver.managers.DriverManager;
import org.openqa.selenium.WebDriver;

public final class Driver {
    private Driver(){}

    public static void initDriver(){
        WebDriver driver = DriverFactory.initDriver();
        DriverManager.setDriver(driver);
    }
}
