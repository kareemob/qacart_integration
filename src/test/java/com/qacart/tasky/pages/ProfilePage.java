package com.qacart.tasky.pages;

import com.qacart.tasky.config.ConfigFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class ProfilePage {
    private final By subscriptionType = By.cssSelector(".MuiChip-label.MuiChip-labelMedium.css-14vsv3w");
    private final By currentPasswordInput = By.cssSelector("[name='currentPassword']");
    private final By newPasswordInput = By.cssSelector("[name='newPassword']");
    private final By updateProfileButton = By.cssSelector(".MuiButton-colorPrimary");
    private final By successToastMessage = By.cssSelector(".Toastify__toast");

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    @Step("Get subscription type")
    public String getSubscriptionType(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(subscriptionType)).getAttribute("innerText");
    }
    @Step("Change password")
    public void changePassword(String currentPassword, String newPassword){
        wait(3).until(ExpectedConditions.visibilityOfElementLocated(currentPasswordInput)).sendKeys(currentPassword);
        getDriver().findElement(newPasswordInput).sendKeys(newPassword);
        getDriver().findElement(updateProfileButton).click();
    }
    @Step("Is message displayed")
    public boolean verifyToastMessage(String messageText){
        return getDriver().findElements(successToastMessage)
                .stream()
                .anyMatch(toast -> toast.getAttribute("innerText").contains(messageText));
    }
    @Step("Navigate to profile page")
    public void load() {
        getDriver().get(ConfigFactory.getConfig().url() + "/dashboard/profile");
    }
}
