package com.qacart.tasky.testcases.e2e;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.components.SideBarComponent;
import com.qacart.tasky.fixtures.UserFixtures;
import com.qacart.tasky.mocks.auth.profile.ProfileStub;
import com.qacart.tasky.pages.LoginPage;
import com.qacart.tasky.pages.ProfilePage;
import com.qacart.tasky.utils.DataUtils;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Profile Module")
public class ProfileTest extends BaseTest {
    private LoginPage loginPage;
    private SideBarComponent sideBarComponent;
    private ProfilePage profilePage;

    @BeforeMethod(groups = "e2e")
    void initPages() {
        loginPage = new LoginPage();
        sideBarComponent = new SideBarComponent();
        profilePage = new ProfilePage();
    }

    @Test(description = "User Should be Able to Change Password", groups = "e2e")
    void userShouldBeAbleToChangeHisPassword() {
        loginPage.load();
        loginPage.simulateLoginFreeUser(UserFixtures.getDefaultUser());
        profilePage.load();
        profilePage.changePassword(DataUtils.getPassword(), "Test4321");
        Assert.assertTrue(profilePage.verifyToastMessage("Profile updated successfully"));
    }
}
