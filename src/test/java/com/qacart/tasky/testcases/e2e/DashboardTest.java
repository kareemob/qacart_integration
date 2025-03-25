package com.qacart.tasky.testcases.e2e;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.fixtures.CardFixture;
import com.qacart.tasky.fixtures.UserFixtures;
import com.qacart.tasky.pages.DashboardPage;
import com.qacart.tasky.pages.LoginPage;

import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.qacart.tasky.fixtures.UserFixtures.getDefaultUser;
@Epic("Dashboard Module")
public class DashboardTest extends BaseTest{
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    @BeforeMethod(groups = "e2e")
    void initPage(){
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @Test(description = "Free user Should See a Free Status Inside Dashboard", groups = "e2e")
    void freeUserShouldSeeFreeStatusInsideDashboard() {
        loginPage.load();
        loginPage.simulateLoginFreeUser(UserFixtures.getDefaultUser());
        dashboardPage.load();
        Assert.assertTrue(dashboardPage.getSubscriptionType().contains("Free"));
    }

    @Test(description = "Advanced user Should See a Advanced Status Inside Dashboard", groups = "e2e")
    void advancedUserShouldSeeAdvancedStatusInsideDashboard() {
        loginPage.load();
        loginPage.simulateLoginAdvancedUser(UserFixtures.getDefaultUser(), CardFixture.getDummyCard());
        dashboardPage.load();
        Assert.assertTrue(dashboardPage.getSubscriptionType().contains("Advanced"));
    }


}
