package com.qacart.tasky.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class CancelSubscriptionComponent {
    private final By cancelSubscriptionButton = By.xpath("//button[text()='Cancel Subscription']");

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    @Step("Cancel subscription")
    public void cancelSubscription(){
        wait(3).until(ExpectedConditions.visibilityOfElementLocated(cancelSubscriptionButton));
        getDriver().findElements(cancelSubscriptionButton).get(1).click();
    }
}
