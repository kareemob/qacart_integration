package com.qacart.tasky.testcases.integration;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.components.SideBarComponent;
import com.qacart.tasky.pages.LoginPage;
import com.qacart.tasky.utils.DataUtils;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Login Module")
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private SideBarComponent sideBarComponent;

    @BeforeMethod(groups = "integration")
    void initPages() {
        loginPage = new LoginPage();
        sideBarComponent = new SideBarComponent();
    }

    @Test(description = "User Should be Able to Login With Correct Credentials", groups = "integration")
    void userShouldBeAbleToLoginWithCorrectCredentials() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        Assert.assertTrue(sideBarComponent.isDashboardIconDisplayed());
    }

    @Test(description = "User Should be Able to Login With Incorrect Credentials", groups = "integration")
    void userShouldBeAbleToLoginWithInCorrectCredentials() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), false, true);
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isInvalidCredentialsMessageDisplayed());
    }

    @Test(description = "User Should not be Able to Login With Empty Email", groups = "integration")
    void userShouldNotBeAbleToLoginWithEmptyEmail() {
        loginPage.load();
        loginPage.fillUserDetails("", DataUtils.getPassword());
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isFillAllFieldsAlertDisplayed());
    }

    @Test(description = "User Should not be Able to Login With Empty Password", groups = "integration")
    void userShouldNotBeAbleToLoginWithEmptyPassword() {
        loginPage.load();
        loginPage.fillUserDetails(DataUtils.generateUniqueEmail(), "");
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isFillAllFieldsAlertDisplayed());
    }
}
