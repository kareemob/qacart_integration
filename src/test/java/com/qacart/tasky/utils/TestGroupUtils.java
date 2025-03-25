package com.qacart.tasky.utils;

import org.testng.ITestResult;
import org.testng.Reporter;

public final class TestGroupUtils {
    private TestGroupUtils(){}

    public static boolean getCurrentTestPackage() {
        try {
            ITestResult result = Reporter.getCurrentTestResult();
            if (result == null) {
                return false;
            }
            String packageName = result.getTestClass().getRealClass().getPackage().getName();
            return packageName.contains(".e2e.");

        } catch (Exception e) {
            return false;
        }
    }
}
