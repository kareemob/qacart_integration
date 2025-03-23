package com.qacart.tasky.driver.factory;

import com.qacart.tasky.driver.managers.ChromeManager;
import com.qacart.tasky.driver.managers.FirefoxManager;
import org.openqa.selenium.WebDriver;

public final class DriverFactory {
    private DriverFactory(){}


    public static WebDriver initDriver(){
        WebDriver driver;
        String browserType = System.getProperty("browser", "CHROME");
        switch (browserType.toLowerCase()){
            case "chrome" -> driver = ChromeManager.getChromeDriver();
            case "firefox" -> driver = FirefoxManager.getFireFoxDriver();
            default -> throw new RuntimeException(browserType + " is not supported");
        }

        return driver;
    }
}
