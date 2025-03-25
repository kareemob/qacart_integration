package com.qacart.tasky.pages;


import com.qacart.tasky.client.SubscriptionClient;
import com.qacart.tasky.client.UserClient;
import com.qacart.tasky.config.ConfigFactory;
import com.qacart.tasky.mocks.auth.login.LoginStub;
import com.qacart.tasky.mocks.auth.profile.ProfileStub;
import com.qacart.tasky.models.Card;
import com.qacart.tasky.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;


public class LoginPage {
    private final By loginButton = By.cssSelector("[type='submit']");
    private final By emailInput = By.cssSelector("[name='email']");
    private final By passwordInput = By.cssSelector("[name='password']");
    private final By fillFieldsAlert = By.cssSelector("div[role='alert']");
    private final By emailAndPasswordFieldSet = By.cssSelector(".Mui-error .MuiOutlinedInput-notchedOutline");
    private final By invalidCredentialsToastMessage = By.cssSelector(".Toastify__toast");
    private final By successRegistrationToast = By.cssSelector("div.Toastify__toast.Toastify__toast--success");

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }
    @Step("Navigate to login page")
    public void load(){
        getDriver().get(ConfigFactory.getConfig().url() + "/login");
    }
    @Step("Click sign in button")
    public void clickSignIn(){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
    }
    @Step("Fill login details")
    public void fillUserDetails(String email, String password){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }
    @Step("Mocked login")
    public void mockLogin(String email, String password, boolean isRegistered, boolean isFree){
        if (isRegistered){
            LoginStub.successLogin();
        }else {
            LoginStub.unAuthorizedLogin();
        }
        if (isFree){
            ProfileStub.successProfileFree();
        }else {
            ProfileStub.successProfileAdvanced();
        }

        wait(5).until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        getDriver().findElement(passwordInput).sendKeys(password);
        getDriver().findElement(loginButton).click();
    }
    @Step("Is warning message for missing fields displayed")
    public boolean isFillAllFieldsAlertDisplayed(){
        WebElement fieldSet = getDriver().findElement(emailAndPasswordFieldSet);
        String borderColor = fieldSet.getCssValue("border-color").toLowerCase();
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(fillFieldsAlert)).isDisplayed() && borderColor.contains("244, 67, 54");
    }
    @Step("Is invalid credentials message displayed")
    public boolean isInvalidCredentialsMessageDisplayed(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(invalidCredentialsToastMessage)).isDisplayed();
    }
    @Step("Is Success message displayed")
    public boolean isSuccessRegistrationMessageDisplayed(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(successRegistrationToast)).isDisplayed();
    }
    @Step("Simulate login")
    public String simulateLoginFreeUser(User user){
        UserClient.registerApi(user);
        Response response = UserClient.loginApi(user);
        User returnedUser = response.body().as(User.class);
        Instant expiryInstant = Instant.now().plus(24, ChronoUnit.HOURS);
        Date expiryDate = Date.from(expiryInstant);
        Cookie userCookie = new Cookie.Builder("access_token", returnedUser.getToken())
                .domain(".qacart.com")
                .expiresOn(expiryDate)
                .path("/")
                .build();
        getDriver().manage().addCookie(userCookie);
        return returnedUser.getToken();
    }

    @Step("Simulate login")
    public String simulateLoginAdvancedUser(User user, Card card){
        UserClient.registerApi(user);
        Response response = UserClient.loginApi(user);
        User returnedUser = response.body().as(User.class);
        SubscriptionClient.subscribeApi(card, returnedUser.getToken());
        Instant expiryInstant = Instant.now().plus(24, ChronoUnit.HOURS);
        Date expiryDate = Date.from(expiryInstant);
        Cookie userCookie = new Cookie.Builder("access_token", returnedUser.getToken())
                .domain(".qacart.com")
                .expiresOn(expiryDate)
                .path("/")
                .build();
        getDriver().manage().addCookie(userCookie);
        return returnedUser.getToken();
    }
    @Step("Get user token")
    public String getUserToken(User user){
        Response response = UserClient.loginApi(user);
        User returnedUser = response.body().as(User.class);
        return returnedUser.getToken();
    }
}
