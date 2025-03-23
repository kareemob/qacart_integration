package com.qacart.tasky.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class UpgradeSubscriptionComponent {
    private final By cardNumberInput = By.cssSelector("[name='cardNumber']");
    private final By expiryMonthInput = By.cssSelector("[name='expiryMonth']");
    private final By expiryYearInput = By.cssSelector("[name='expiryYear']");
    private final By cvvInput = By.cssSelector("[name='cvv']");
    private final By subscribeButton = By.xpath("//button[contains(text(),'Subscribe')]");

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }
    @Step("Fill card details")
    public void fillCardDetails(String cardNumber, String expiryMonth, String expiryYear, String cvv){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput)).sendKeys(cardNumber);
        getDriver().findElement(expiryMonthInput).sendKeys(expiryMonth);
        getDriver().findElement(expiryYearInput).sendKeys(expiryYear);
        getDriver().findElement(cvvInput).sendKeys(cvv);
    }
    @Step("Click subscribe")
    public void clickSubscribeButton(){
        getDriver().findElement(subscribeButton).click();
    }
}
