package com.qacart.tasky.pages;

import com.qacart.tasky.components.CancelSubscriptionComponent;
import com.qacart.tasky.components.UpgradeSubscriptionComponent;
import com.qacart.tasky.config.ConfigFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class SubscriptionPage {
    private final By upgradeButton = By.xpath("//button[contains(normalize-space(),'Upgrade')]");
    private final By successSubscriptionMessage = By.xpath("//div[contains(text(), 'Successfully upgraded to advanced')]");
    private final By currentSubscriptionStatus = By.cssSelector(".MuiPaper-root.MuiPaper-elevation.MuiPaper-rounded.MuiPaper-elevation1.css-byi6fd");
    private final By cancelSubscriptionButton = By.xpath("//button[text()='Cancel Subscription']");
    private final By successToastMessage = By.cssSelector(".Toastify__toast");
    private final By subscriptionExpiringAlert = By.cssSelector(".MuiAlert-message");
    private final By cardNumberInput = By.cssSelector("[name='cardNumber']");
    private final By expiryMontInput = By.cssSelector("[name='expiryMonth']");
    private final By expiryYearInput = By.cssSelector("[name='expiryYear']");
    private final By cvvInput = By.cssSelector("[name='cvv']");
    private final By subscribeButton = By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.MuiButton-colorPrimary.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.MuiButton-colorPrimary.css-1dzsn8i");
    public final UpgradeSubscriptionComponent upgradeSubscriptionComponent = new UpgradeSubscriptionComponent();
    public final CancelSubscriptionComponent cancelSubscriptionComponent = new CancelSubscriptionComponent();

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    @Step("Click upgrade button")
    public void clickUpgrade(){
        wait(4).until(ExpectedConditions.visibilityOfElementLocated(upgradeButton)).click();
    }

    @Step("Is successfull subscription message desplayed")
    public boolean isSuccessSubscriptionMessage(){
        return wait(4).until(ExpectedConditions.visibilityOfElementLocated(successSubscriptionMessage)).isDisplayed();
    }
    @Step("Is subscription status displayed")
    public boolean isSubscriptionStatusDisplayed(){
        return getDriver().findElements(currentSubscriptionStatus).stream()
                .findFirst()
                .map(WebElement::isDisplayed)
                .orElse(false);
    }
    @Step("Click cancel subscription")
    public void clickCancelSubscription(){
        wait(2).until(ExpectedConditions.visibilityOfElementLocated(cancelSubscriptionButton)).click();
    }
    @Step("Is success message displayed")
    public boolean verifySuccessToastMessage(String messageText){
        wait(5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(successToastMessage));
        List<WebElement> successToast = getDriver().findElements(successToastMessage);
        return successToast.stream().anyMatch(toast -> toast.getAttribute("innerText").contains(messageText));
    }
    @Step("Is subscription expire warning displayed")
    public boolean isSubscriptionExpiringWarningDisplayed(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(subscriptionExpiringAlert)).isDisplayed();
    }
    @Step("Navigate to subscriptions page")
    public void load() {
        getDriver().get(ConfigFactory.getConfig().url() + "/dashboard/subscription");
    }
    @Step("Fill card details")
    public void fillCardDetails(String cardNumber, String expiryMonth, String expiryYear, String cvv){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput)).sendKeys(cardNumber);
        getDriver().findElement(expiryMontInput).sendKeys(expiryMonth);
        getDriver().findElement(expiryYearInput).sendKeys(expiryYear);
        getDriver().findElement(cvvInput).sendKeys(cvv);
    }
    @Step("Click subscribe")
    public void clickSubscribe(){
        getDriver().findElement(subscribeButton).click();
    }
}
