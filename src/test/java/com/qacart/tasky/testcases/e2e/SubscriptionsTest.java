package com.qacart.tasky.testcases.e2e;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.components.SideBarComponent;
import com.qacart.tasky.config.ConfigFactory;
import com.qacart.tasky.fixtures.CardFixture;
import com.qacart.tasky.fixtures.UserFixtures;
import com.qacart.tasky.mocks.auth.profile.ProfileStub;
import com.qacart.tasky.mocks.subscriptions.CancelSubscriptionStub;
import com.qacart.tasky.mocks.subscriptions.GetCurrentSubscriptionStateStub;
import com.qacart.tasky.mocks.subscriptions.GetSubscriptionPlansStub;
import com.qacart.tasky.mocks.subscriptions.SubscribeStub;
import com.qacart.tasky.pages.LoginPage;
import com.qacart.tasky.pages.SubscriptionPage;
import com.qacart.tasky.utils.DataUtils;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Subscription Module")
public class SubscriptionsTest extends BaseTest {
    private LoginPage loginPage;
    private SideBarComponent sideBarComponent;
    private SubscriptionPage subscriptionPage;

    @BeforeMethod(alwaysRun = true)
    void initPages() {
        loginPage = new LoginPage();
        sideBarComponent = new SideBarComponent();
        subscriptionPage = new SubscriptionPage();
    }

    @Test(description = "Free user Should be Able to Upgrade to Advanced Plan", groups = "e2e")
    void freeUserShouldBeAbleToUpdateToAdvancedPlan(){
        loginPage.load();
        loginPage.simulateLoginFreeUser(UserFixtures.getDefaultUser());
        subscriptionPage.load();
        subscriptionPage.clickUpgrade();
        subscriptionPage.fillCardDetails(ConfigFactory.getConfig().cardNumber(), ConfigFactory.getConfig().expiryMonth(), ConfigFactory.getConfig().expiryYear(), ConfigFactory.getConfig().cvv());
        subscriptionPage.clickSubscribe();
        Assert.assertTrue(subscriptionPage.verifySuccessToastMessage("Successfully upgraded to advanced"));
    }

    @Test(description = "Advanced user Should be Able to Cancel Subscription", groups = "e2e")
    void advancedUserShouldBeAbleToToCancelSubscription(){
        loginPage.load();
        loginPage.simulateLoginAdvancedUser(UserFixtures.getDefaultUser(), CardFixture.getDummyCard());
        subscriptionPage.load();
        subscriptionPage.clickCancelSubscription();
        subscriptionPage.cancelSubscriptionComponent.cancelSubscription();
        Assert.assertTrue(subscriptionPage.verifySuccessToastMessage("Subscription successfully cancelled"));
    }
}
