package com.qacart.tasky.driver.managers;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ProxyChromeManager {
    private ProxyChromeManager(){}

    public static WebDriver getChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8088").setSslProxy("localhost:8088");
        options.setProxy(proxy);
        options.addArguments("--ignore-certificate-errors", "--incognito", "--disable-web-security");
        return new ChromeDriver(options);
    }
}
