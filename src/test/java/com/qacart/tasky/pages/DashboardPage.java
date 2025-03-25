package com.qacart.tasky.pages;

import com.qacart.tasky.config.ConfigFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class DashboardPage {
    private final By subscriptionDialog = By.cssSelector("#subscription-dialog-title");
    private final By subscriptionType = By.cssSelector(".MuiTypography-root.MuiTypography-h4.css-lpo7vw");
    private final By subscriptionDueWarning = By.cssSelector(".MuiTypography-root.MuiTypography-body2.css-1sr1mef");
    private final By renewSubscriptionButton = By.cssSelector("[data-testid=RefreshIcon]");
    private final By manageSubscriptionButton = By.cssSelector("[data-testid=StarIcon]");
    private final By manageTodosButton = By.cssSelector("[data-testid=AssignmentIcon]");


    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }
    @Step("Navigate to dashboard page")
    public void load(){
        getDriver().get(ConfigFactory.getConfig().url() + "/dashboard");
    }
    @Step("Is subscription dialog displayed")
    public boolean isSubscriptionDialogDisplayed(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(subscriptionDialog)).isDisplayed();
    }
    @Step("Get status type")
    public List<String> getSubscriptionType(){
        return Collections.singletonList(getDriver().findElement(subscriptionType).getText());
    }
    @Step("Is success message displayed")
    public boolean isWarningMessageRetrievedCorrectly(String messageText) {
        return getDriver().findElements(subscriptionDueWarning)
                .stream()
                .anyMatch(toast -> toast.getAttribute("innerText").contains(messageText));
    }
    @Step("Click renew subscription button")
    public void clickRenewSubscription(){
        wait(3).until(ExpectedConditions.visibilityOfElementLocated(renewSubscriptionButton));
        getDriver().findElements(renewSubscriptionButton).get(1).click();
    }
    @Step("Click manage todo button")
    public void clickManageTodos(){
        wait(3).until(ExpectedConditions.visibilityOfElementLocated(manageTodosButton)).click();
    }
    @Step("Click manage todo button")
    public void clickManageSubscription(){
        wait(3).until(ExpectedConditions.visibilityOfElementLocated(manageSubscriptionButton)).click();
    }
}
