package com.qacart.tasky.base;

import com.qacart.tasky.driver.Driver;
import com.qacart.tasky.server.ServerManager;
import com.qacart.tasky.utils.ScreenshotUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class Base {
    @BeforeMethod(groups = {"integration", "e2e"})
    protected void driverSetup(){
        ServerManager.startServer();
        Driver.initDriver();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
    }

    @AfterMethod(groups = {"integration", "e2e"})
    protected void tearDown(ITestResult testResult){
        ScreenshotUtils.takeScreenshot(testResult);
        getDriver().quit();
        ServerManager.stopServer();
    }
}
