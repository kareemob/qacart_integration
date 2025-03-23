package com.qacart.tasky.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class SideBarComponent {
    private final By dashboardIcon = By.cssSelector("[data-testid=DashboardIcon]");
    private final By todoButton = By.cssSelector("[data-testid=FormatListBulletedIcon]");
    private final By subscriptionButton = By.cssSelector("[data-testid=SubscriptionsIcon]");
    private final By profileButton = By.xpath("(//div[@role='button'])[4]");

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }
    @Step("Is dashboard icon displayed")
    public boolean isDashboardIconDisplayed(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(dashboardIcon)).isDisplayed();
    }
    @Step("Click todos button")
    public void clickTodos(){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(todoButton)).click();
    }

    @Step("Click subscription button")
    public void clickSubscriptionButton(){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(subscriptionButton)).click();
    }

    @Step("Click profile button")
    public void clickProfileButton(){
        wait(5).until(ExpectedConditions.presenceOfElementLocated(profileButton)).click();
    }
}
