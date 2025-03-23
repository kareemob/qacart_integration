package com.qacart.tasky.testcases;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.components.SideBarComponent;
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

    @BeforeMethod
    void initPages() {
        loginPage = new LoginPage();
        sideBarComponent = new SideBarComponent();
        subscriptionPage = new SubscriptionPage();
    }

    @Test(description = "Free user Should be Able to Upgrade to Advanced Plan")
    void freeUserShouldBeAbleToUpdateToAdvancedPlan(){
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetSubscriptionPlansStub.getSubscriptionPlansMocked();
        sideBarComponent.clickSubscriptionButton();
        subscriptionPage.clickUpgrade();
        SubscribeStub.subscribeMocked();
        ProfileStub.successProfileAdvanced();
        GetCurrentSubscriptionStateStub.currentSubscribedMocked();
        subscriptionPage.upgradeSubscriptionComponent.fillCardDetails(DataUtils.getCardNumber(),DataUtils.getCardExpiryMonth(),DataUtils.getCardExpiryYear(),DataUtils.getCardCvv());
        subscriptionPage.upgradeSubscriptionComponent.clickSubscribeButton();
        Assert.assertTrue(subscriptionPage.verifySuccessToastMessage("Successfully upgraded to advanced"));
    }

    @Test(description = "Free user Should not be Able to See Current Status in Subscription Page")
    void freeUserShouldNotBeAbleToToSeeCurrentStatus(){
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetSubscriptionPlansStub.getSubscriptionPlansMocked();
        sideBarComponent.clickSubscriptionButton();
        Assert.assertFalse(subscriptionPage.isSubscriptionStatusDisplayed());
    }

    @Test(description = "Advanced user Should be Able to See Current Status in Subscription Page")
    void advancedUserShouldBeAbleToToSeeCurrentStatus(){
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        GetSubscriptionPlansStub.getSubscriptionPlansMocked();
        GetCurrentSubscriptionStateStub.currentSubscribedMocked();
        sideBarComponent.clickSubscriptionButton();
        Assert.assertTrue(subscriptionPage.isSubscriptionStatusDisplayed());
    }

    @Test(description = "Advanced user Should be Able to Cancel Subscription")
    void advancedUserShouldBeAbleToToCancelSubscription(){
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        GetSubscriptionPlansStub.getSubscriptionPlansMocked();
        GetCurrentSubscriptionStateStub.currentSubscribedMocked();
        sideBarComponent.clickSubscriptionButton();
        CancelSubscriptionStub.cancelSubscription();
        subscriptionPage.clickCancelSubscription();
        subscriptionPage.cancelSubscriptionComponent.cancelSubscription();
        Assert.assertTrue(subscriptionPage.verifySuccessToastMessage("Subscription successfully cancelled"));
    }

    @Test(description = "Advanced user Should See a Warning When the Expiry Date is due 2 Days")
    void advancedUserShouldSeeWarningWhenExpiryDateIsDueTwoDays() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        GetCurrentSubscriptionStateStub.currentSubscribedTwoDaysLeft();
        GetSubscriptionPlansStub.getSubscriptionPlansMocked();
        sideBarComponent.clickSubscriptionButton();
        Assert.assertTrue(subscriptionPage.isSubscriptionExpiringWarningDisplayed());
    }
}
