package com.qacart.tasky.testcases.integration;

import com.qacart.tasky.base.Base;
import com.qacart.tasky.components.SideBarComponent;
import com.qacart.tasky.mocks.auth.profile.ProfileStub;
import com.qacart.tasky.pages.*;
import com.qacart.tasky.utils.DataUtils;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Profile Module")
public class ProfileTest extends Base {
    private LoginPage loginPage;
    private SideBarComponent sideBarComponent;
    private ProfilePage profilePage;

    @BeforeMethod(groups = "integration")
    void initPages() {
        loginPage = new LoginPage();
        sideBarComponent = new SideBarComponent();
        profilePage = new ProfilePage();
    }

    @Test(description = "Free User Should be Able to See `Regular User` Inside Profile Page", groups = "integration")
    void freeUserShouldSeeHisTypeInsideProfilePage(){
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        sideBarComponent.clickProfileButton();
        Assert.assertEquals(profilePage.getSubscriptionType(), "Regular User");
    }

    @Test(description = "Advanced User Should be Able to See `Advanced User` Inside Profile Page", groups = "integration")
    void advancedUserShouldSeeHisTypeInsideProfilePage(){
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        sideBarComponent.clickProfileButton();
        Assert.assertEquals(profilePage.getSubscriptionType(), "Advanced User");
    }

    @Test(description = "User Should be Able to Change Password", groups = "integration")
    void userShouldBeAbleToChangeHisPassword() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        sideBarComponent.clickProfileButton();
        ProfileStub.successProfileUpdate();
        profilePage.changePassword(DataUtils.getPassword(), DataUtils.getPassword());
        Assert.assertTrue(profilePage.verifyToastMessage("Profile updated successfully"));
    }

    @Test(description = "User Should not be Able to Change Password with Incorrect Current Password", groups = "integration")
    void userShouldNotBeAbleToChangeHisPasswordWithInCorrectCurrentPassword() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        sideBarComponent.clickProfileButton();
        ProfileStub.failProfileUpdate();
        profilePage.changePassword(DataUtils.getPassword(), DataUtils.getPassword());
        Assert.assertTrue(profilePage.verifyToastMessage("Incorrect current password"));
    }
}
