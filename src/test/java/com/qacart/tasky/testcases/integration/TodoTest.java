package com.qacart.tasky.testcases.integration;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.components.SideBarComponent;
import com.qacart.tasky.mocks.auth.profile.ProfileStub;
import com.qacart.tasky.mocks.subscriptions.GetCurrentSubscriptionStateStub;
import com.qacart.tasky.mocks.subscriptions.GetSubscriptionPlansStub;
import com.qacart.tasky.mocks.subscriptions.SubscribeStub;
import com.qacart.tasky.mocks.todos.AddTodosStub;
import com.qacart.tasky.mocks.todos.DeleteTodoStub;
import com.qacart.tasky.mocks.todos.EditTodoStub;
import com.qacart.tasky.mocks.todos.GetTodosStub;
import com.qacart.tasky.pages.LoginPage;
import com.qacart.tasky.pages.SubscriptionPage;
import com.qacart.tasky.pages.TodosPage;
import com.qacart.tasky.utils.DataUtils;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Todo Module")
public class TodoTest extends BaseTest {
    private LoginPage loginPage;
    private TodosPage todosPage;
    private SideBarComponent sideBarComponent;
    private SubscriptionPage subscriptionPage;

    @BeforeMethod
    void initPages() {
        loginPage = new LoginPage();
        todosPage = new TodosPage();
        sideBarComponent = new SideBarComponent();
        subscriptionPage = new SubscriptionPage();
    }

    @Test(description = "User Should see `No todos yet. Create your first todo!` When There is no Todos")
    void userShouldSeeNoTodosMessageWhenThereIsNoTodos() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetTodosStub.emptyTodo();
        sideBarComponent.clickTodos();
        Assert.assertTrue(todosPage.isNoTodosMessageDisplayed());
    }

    @Test(description = "User Should be Able to Add Todo")
    void userShouldBeAbleToAddTodo() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetTodosStub.emptyTodo();
        sideBarComponent.clickTodos();
        todosPage.clickAddTodo();
        AddTodosStub.addNewTodo();
        todosPage.addNewTodoComponent.fillTodoTitle("Learn Wiremock").clickAddButton();
        Assert.assertTrue(todosPage.verifySuccessToastMessage("Todo created successfully"));
    }

    @Test(description = "Free User Should not be Able to Edit Todo")
    void freeUserShouldNotBeAbleToEditTodo() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetTodosStub.fullTodosForFreeUser();
        sideBarComponent.clickTodos();
        Assert.assertTrue(todosPage.loadTodos().get(0).isEditButtonLocked());
    }

    @Test(description = "Cancel Button on Add New Todo Button Should Eliminate Add New Todo Component")
    void CancelAddNewTodoButtonShouldEliminateAddNewTodoComponent(){
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetTodosStub.emptyTodo();
        sideBarComponent.clickTodos();
        todosPage.clickAddTodo();
        todosPage.addNewTodoComponent.clickCancelButton();
        Assert.assertFalse(todosPage.addNewTodoComponent.isAddNewTodoComponentVisible());
    }

    @Test(description = "User Should be Able to Delete Todo")
    void userShouldBeAbleToDeleteTodo() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetTodosStub.fullTodosForFreeUser();
        sideBarComponent.clickTodos();
        DeleteTodoStub.deleteTodo();
        todosPage.loadTodos().get(0).clickDeleteIcon();
        Assert.assertTrue(todosPage.verifySuccessToastMessage("Todo deleted successfully"));
    }

    @Test(description = "User Should not be Able to Add More than 3 Todos")
    void freeUserShouldNotBeAbleToAddMoreThan3Todos() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetTodosStub.fullTodosForFreeUser();
        sideBarComponent.clickTodos();
        Assert.assertTrue(todosPage.isAddButtonDisabled());
    }

    @Test(description = "Free User Should See a Warning when Reaching 3 Todos")
    void freeUserShouldShouldSeeWarningWhenReach3Todos() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetTodosStub.fullTodosForFreeUser();
        sideBarComponent.clickTodos();
        Assert.assertTrue(todosPage.isFreeUserWarningForTodoLimitDisplayed());
    }

    @Test(description = "Upgraded user should not see any warnings")
    void upgradedUserShouldNotSeeAnyWarnings() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, true);
        GetTodosStub.fullTodosForFreeUser();
        sideBarComponent.clickTodos();
        todosPage.isFreeUserWarningForTodoLimitDisplayed();
        todosPage.isUpgradeMessageDisplayed();
        GetSubscriptionPlansStub.getSubscriptionPlansMocked();
        sideBarComponent.clickSubscriptionButton();
        subscriptionPage.clickUpgrade();
        SubscribeStub.subscribeMocked();
        ProfileStub.successProfileAdvanced();
        GetCurrentSubscriptionStateStub.currentSubscribedMocked();
        subscriptionPage.upgradeSubscriptionComponent.fillCardDetails(DataUtils.getCardNumber(),DataUtils.getCardExpiryMonth(),DataUtils.getCardExpiryYear(),DataUtils.getCardCvv());
        GetCurrentSubscriptionStateStub.currentSubscribedMocked();
        subscriptionPage.upgradeSubscriptionComponent.clickSubscribeButton();
        GetTodosStub.fullTodosForFreeUser();
        sideBarComponent.clickTodos();
        Assert.assertTrue(todosPage.areFreeAlertsNotDisplayed());
    }

    @Test(description = "Advanced user should be able to add more than 3 todos")
    void advancedUserShouldBeAbleToAddMoreThan3Todos() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        GetCurrentSubscriptionStateStub.currentSubscribedMocked();
        GetTodosStub.fullTodosForFreeUser();
        sideBarComponent.clickTodos();
        AddTodosStub.addNewTodo();
        GetTodosStub.moreThan3Todos();
        todosPage.clickAddTodo();
        todosPage.addNewTodoComponent.fillTodoTitle("Read a book").clickAddButton();
        Assert.assertTrue(todosPage.verifySuccessToastMessage("Todo created successfully"));
    }

    @Test(description = "Advanced user should be able to edit todos")
    void advancedUserShouldBeAbleToEditTodo() {
        loginPage.load();
        loginPage.mockLogin(DataUtils.generateUniqueEmail(), DataUtils.getPassword(), true, false);
        GetCurrentSubscriptionStateStub.currentSubscribedMocked();
        GetTodosStub.fullTodosForFreeUser();
        sideBarComponent.clickTodos();
        EditTodoStub.updateTodo();
        GetTodosStub.getTodoAfterEdit();
        todosPage.loadTodos().get(0).clickEditTodoButton();
        todosPage.editTodoComponent.editTodo("-edited").clickUpdateTodo();
        Assert.assertTrue(todosPage.verifySuccessToastMessage("Todo updated successfully"));
    }
}
