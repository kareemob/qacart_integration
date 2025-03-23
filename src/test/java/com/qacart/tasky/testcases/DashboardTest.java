package com.qacart.tasky.testcases;


import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.mocks.subscriptions.GetCurrentSubscriptionStateStub;
import com.qacart.tasky.pages.DashboardPage;
import com.qacart.tasky.pages.LoginPage;
import com.qacart.tasky.pages.SubscriptionPage;
import com.qacart.tasky.pages.TodosPage;
import com.qacart.tasky.utils.DataUtils;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Dashboard Module")
public class DashboardTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private SubscriptionPage subscriptionPage;
    private TodosPage todosPage;

    @BeforeMethod
    void initPages() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        subscriptionPage = new SubscriptionPage();
        todosPage = new TodosPage();
    }

    @Test(description = "Free user Should See a Free Status Inside Dashboard")
    void freeUserShouldSeeFreeStatusInsideDashboard() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        Assert.assertTrue(dashboardPage.getSubscriptionType().contains("Free"));
    }

    @Test(description = "Advanced user Should See a Advanced Status Inside Dashboard")
    void advancedUserShouldSeeAdvancedStatusInsideDashboard() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        Assert.assertTrue(dashboardPage.getSubscriptionType().contains("Advanced"));
    }

    @Test(description = "Advanced user Should See a Warning When the Expiry Date is due 2 Days")
    void advancedUserShouldSeeWarningWhenExpiryDateIsDueTwoDays() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        GetCurrentSubscriptionStateStub.currentSubscribedTwoDaysLeft();
        Assert.assertTrue(dashboardPage.isWarningMessageRetrievedCorrectly("Your subscription is about to expire!"));
    }

    @Test(description = "Advanced User Should be Redirected to Subscription Page When Click Renew From Quick Actions")
    void userShouldBeRedirectedToSubscriptionPageWhenClickRenewFromQuickActions() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        GetCurrentSubscriptionStateStub.currentSubscribedTwoDaysLeft();
        dashboardPage.clickRenewSubscription();
        Assert.assertTrue(subscriptionPage.isSubscriptionExpiringWarningDisplayed());
    }

    @Test(description = "User Should be Redirected to Todos Page when Click Manage Todos from Quick Actions")
    void UserShouldBeRedirectedToTodosPageWhenClickManageTodosFromQuickActions() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        dashboardPage.clickManageTodos();
        Assert.assertTrue(todosPage.isUpgradeMessageDisplayed());
    }

    @Test(description = "User Should be Redirected to Subscription Page when Click Manage Subscription from Quick Actions")
    void UserShouldBeRedirectedToSubscriptionPageWhenClickManageSubscriptionFromQuickActions() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        GetCurrentSubscriptionStateStub.currentSubscribedMocked();
        dashboardPage.clickManageSubscription();
        Assert.assertTrue(subscriptionPage.isSubscriptionStatusDisplayed());
    }
}
