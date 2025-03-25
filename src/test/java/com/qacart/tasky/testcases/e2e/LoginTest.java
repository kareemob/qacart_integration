package com.qacart.tasky.testcases.e2e;


import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.components.SideBarComponent;
import com.qacart.tasky.fixtures.UserFixtures;
import com.qacart.tasky.models.User;
import com.qacart.tasky.pages.LoginPage;
import com.qacart.tasky.pages.RegisterPage;
import com.qacart.tasky.utils.DataUtils;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Login Module")
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private SideBarComponent sideBarComponent;
    private RegisterPage registerPage;

    @BeforeMethod(alwaysRun = true)
    void initPages() {
        loginPage = new LoginPage();
        sideBarComponent = new SideBarComponent();
        registerPage = new RegisterPage();
    }

    @Test(description = "User Should be Able to Login With Correct Credentials", groups = "e2e")
    void userShouldBeAbleToLoginWithCorrectCredentials() {
        User user = UserFixtures.getDefaultUser();
        registerPage.registerApi(user.getEmail(), user.getPassword());
        loginPage.load();
        loginPage.fillUserDetails(user.getEmail(), user.getPassword());
        loginPage.clickSignIn();
        Assert.assertTrue(sideBarComponent.isDashboardIconDisplayed());
    }
}