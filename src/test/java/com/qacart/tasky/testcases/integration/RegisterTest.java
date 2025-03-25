package com.qacart.tasky.testcases.integration;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.pages.DashboardPage;
import com.qacart.tasky.pages.LoginPage;
import com.qacart.tasky.pages.RegisterPage;
import com.qacart.tasky.utils.DataUtils;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Registration Module")
public class RegisterTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private RegisterPage registerPage;

    @BeforeMethod
    void initPages() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        registerPage = new RegisterPage();
    }

    @Test(description = "User Should be Able to Register")
    void userShouldBeAbleToRegister(){
        registerPage.load();
        registerPage.mockSuccessRegisterFree();
        registerPage.fillRegistrationInfo(DataUtils.getName(), DataUtils.getName(), DataUtils.generateUniqueEmail(), DataUtils.getPassword(), false);
        registerPage.clickSignupButton();
        Assert.assertTrue(loginPage.isSuccessRegistrationMessageDisplayed());
    }

    @Test(description = "User Should not be Able to Register With Registered Email")
    void userShouldNotBeAbleToRegisterWithRegisteredEmail(){
        registerPage.load();
        registerPage.alreadyExistUserMocked();
        registerPage.fillRegistrationInfo(DataUtils.getName(), DataUtils.getName(), DataUtils.generateUniqueEmail(), DataUtils.getPassword(), false);
        registerPage.clickSignupButton();
        Assert.assertTrue(registerPage.isErrorMessageDisplayed());
    }

    @Test(description = "User Should not be Able to Register With Invalid Email")
    void userShouldNotBeAbleToRegisterWithInvalidEmail(){
        registerPage.load();
        registerPage.fillRegistrationInfo(DataUtils.getName(), DataUtils.getName(), "test@ex", DataUtils.getPassword(), true);
        registerPage.clickSignupButton();
        Assert.assertTrue(registerPage.isMissingFieldsErrorDisplayed());
    }

    @Test(description = "Subscription Dialog Should Appear When User Select Advanced Plan")
    void subscriptionDialogShouldAppearWhenUserSelectAdvancedPlan(){
        registerPage.load();
        registerPage.mockSuccessRegisterFree();
        String email = DataUtils.generateUniqueEmail();
        registerPage.fillRegistrationInfo(DataUtils.getName(), DataUtils.getName(), email, DataUtils.getPassword(), true);
        registerPage.clickSignupButton();
        loginPage.mockLogin(email, DataUtils.getPassword(), true, true);
        Assert.assertTrue(dashboardPage.isSubscriptionDialogDisplayed());
    }

    @Test(description = "user Should not be Able to Register With Empty First Name")
    void userShouldNotBeAbleToRegisterWithEmptyFirstName(){
        registerPage.load();
        registerPage.fillRegistrationInfo("", DataUtils.getName(), DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true);
        registerPage.clickSignupButton();
        Assert.assertTrue(registerPage.isMissingFieldsErrorDisplayed());
    }

    @Test(description = "user Should not be Able to Register With Empty Last Name")
    void userShouldNotBeAbleToRegisterWithEmptyLastName(){
        registerPage.load();
        registerPage.fillRegistrationInfo(DataUtils.getName(), "", DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true);
        registerPage.clickSignupButton();
        Assert.assertTrue(registerPage.isMissingFieldsErrorDisplayed());
    }

    @Test(description = "user Should not be Able to Register With Empty Email")
    void userShouldNotBeAbleToRegisterWithEmptyEmail(){
        registerPage.load();
        registerPage.fillRegistrationInfo(DataUtils.getName(), DataUtils.getName(), "", DataUtils.getPassword(), true);
        registerPage.clickSignupButton();
        Assert.assertTrue(registerPage.isMissingFieldsErrorDisplayed());
    }

    @Test(description = "user Should not be Able to Register With Empty Password")
    void userShouldNotBeAbleToRegisterWithEmptyPassword(){
        registerPage.load();
        registerPage.fillRegistrationInfo(DataUtils.getName(), DataUtils.getName(), DataUtils.generateUniqueEmail(), "", true);
        registerPage.clickSignupButton();
        Assert.assertTrue(registerPage.isMissingFieldsErrorDisplayed());
    }
}
