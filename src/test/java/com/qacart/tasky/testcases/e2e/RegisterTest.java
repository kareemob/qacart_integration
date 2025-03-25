package com.qacart.tasky.testcases.e2e;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.fixtures.UserFixtures;
import com.qacart.tasky.models.User;
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

    @BeforeMethod(groups = "e2e")
    void initPages() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        registerPage = new RegisterPage();
    }

    @Test(description = "User Should be Able to Register", groups = "e2e")
    void userShouldBeAbleToRegister(){
        registerPage.load();
        registerPage.fillRegistrationInfo(DataUtils.getName(), DataUtils.getName(), DataUtils.generateUniqueEmail(), DataUtils.getPassword(), false);
        registerPage.clickSignupButton();
        Assert.assertTrue(loginPage.isSuccessRegistrationMessageDisplayed());
    }

    @Test(description = "Subscription Dialog Should Appear When User Select Advanced Plan", groups = "e2e")
    void subscriptionDialogShouldAppearWhenUserSelectAdvancedPlan(){
        User user = UserFixtures.getDefaultUser();
        registerPage.load();
        registerPage.fillRegistrationInfo(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), true);
        registerPage.clickSignupButton();
        loginPage.simulateLoginFreeUser(user);
        dashboardPage.load();
        Assert.assertTrue(dashboardPage.isSubscriptionDialogDisplayed());
    }
}
