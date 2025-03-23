package com.qacart.tasky.utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public final class ScreenshotUtils {
    private ScreenshotUtils(){}

    public static void takeScreenshot(ITestResult testResult){
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String scPathName = "target" + File.separator + "screenshots" + File.separator + testResult.getMethod().getMethodName() + ".png";
        attachScreenShot(file, scPathName);
    }

    private static void attachScreenShot(File file, String scPathName){
        try {
            FileUtils.copyFile(file, new File(scPathName));
            InputStream inputStream = new FileInputStream(file);
            Allure.attachment("screenshot", inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
