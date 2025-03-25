package com.qacart.tasky.pages;

import com.qacart.tasky.client.UserClient;
import com.qacart.tasky.config.ConfigFactory;
import com.qacart.tasky.fixtures.UserFixtures;
import com.qacart.tasky.mocks.auth.signup.RegisterStub;
import com.qacart.tasky.models.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class RegisterPage {
    private final By firstNameInput = By.cssSelector("[name='firstName']");
    private final By lastNameInput = By.cssSelector("[name='lastName']");
    private final By emailInput = By.cssSelector("[name='email']");
    private final By passwordInput = By.cssSelector("[name='password']");
    private final By advancedUserButton = By.cssSelector("input[value='advanced']");
    private final By signupButton = By.cssSelector("[type='submit']");
    private final By errorToastMessage = By.cssSelector("div.Toastify__toast.Toastify__toast--error");
    private final By fillFieldsAlert = By.cssSelector("div[role='alert']");
    private final By inputFieldSet = By.cssSelector(".Mui-error .MuiOutlinedInput-notchedOutline");

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    @Step("Navigate to register page")
    public void load(){
        getDriver().get(ConfigFactory.getConfig().url() + "/register");
    }
    @Step("Fill registration details")
    public void fillRegistrationInfo(String firstName, String lastName, String email, String password, boolean isAdvanced){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        getDriver().findElement(lastNameInput).sendKeys(lastName);
        getDriver().findElement(emailInput).sendKeys(email);
        getDriver().findElement(passwordInput).sendKeys(password);
        if (isAdvanced){
            getDriver().findElement(advancedUserButton).click();
        }
    }
    @Step("Click signup button")
    public void clickSignupButton(){
        getDriver().findElement(signupButton).click();
    }
    @Step("Successful registration mocked")
    public void mockSuccessRegisterFree(){
        RegisterStub.successRegisterFree();
    }
    @Step("Register with existed user mocked")
    public void alreadyExistUserMocked(){
        RegisterStub.registerWithExistUser();
    }
    @Step("Is error message displayed")
    public boolean isErrorMessageDisplayed(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(errorToastMessage)).isDisplayed();
    }
    @Step("Is missing fields warning displayed")
    public boolean isMissingFieldsErrorDisplayed(){
        boolean isDisplayed = wait(5).until(ExpectedConditions.visibilityOfElementLocated(fillFieldsAlert)).isDisplayed();
        List<WebElement> fieldSet = getDriver().findElements(inputFieldSet);
        String borderColor = null;
        if (fieldSet.size() == 4){
            borderColor = fieldSet.get(0).getCssValue("border-color").toLowerCase();
        }
        return borderColor.contains("244, 67, 54") && isDisplayed;
    }
    @Step("Register api")
    public void registerApi(String email, String password){
        User user = UserFixtures.getDefaultUser(email, password);
        UserClient.registerApi(user);
    }
    @Step("Register api")
    public void registerApi(User user){
        UserClient.registerApi(user);
    }
}
