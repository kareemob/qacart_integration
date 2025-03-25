package com.qacart.tasky.testcases.e2e;

import com.qacart.tasky.base.BaseTest;
import com.qacart.tasky.components.SideBarComponent;
import com.qacart.tasky.fixtures.CardFixture;
import com.qacart.tasky.fixtures.TodoFixture;
import com.qacart.tasky.fixtures.UserFixtures;
import com.qacart.tasky.mocks.auth.profile.ProfileStub;
import com.qacart.tasky.mocks.subscriptions.GetCurrentSubscriptionStateStub;
import com.qacart.tasky.mocks.subscriptions.GetSubscriptionPlansStub;
import com.qacart.tasky.mocks.subscriptions.SubscribeStub;
import com.qacart.tasky.mocks.todos.AddTodosStub;
import com.qacart.tasky.mocks.todos.DeleteTodoStub;
import com.qacart.tasky.mocks.todos.EditTodoStub;
import com.qacart.tasky.mocks.todos.GetTodosStub;
import com.qacart.tasky.models.User;
import com.qacart.tasky.pages.LoginPage;
import com.qacart.tasky.pages.RegisterPage;
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
    private RegisterPage registerPage;

    @BeforeMethod(alwaysRun = true)
    void initPages() {
        loginPage = new LoginPage();
        todosPage = new TodosPage();
        sideBarComponent = new SideBarComponent();
        subscriptionPage = new SubscriptionPage();
        registerPage = new RegisterPage();
    }

    @Test(description = "User Should see `No todos yet. Create your first todo!` When There is no Todos")
    void userShouldSeeNoTodosMessageWhenThereIsNoTodos() {
        loginPage.load();
        loginPage.simulateLoginFreeUser(UserFixtures.getDefaultUser());
        todosPage.load();
        Assert.assertTrue(todosPage.isNoTodosMessageDisplayed());
    }

    @Test(description = "User Should be Able to Add Todo")
    void userShouldBeAbleToAddTodo() {
        loginPage.load();
        loginPage.simulateLoginFreeUser(UserFixtures.getDefaultUser());
        todosPage.load();
        todosPage.clickAddTodo();
        todosPage.addNewTodoComponent.fillTodoTitle("Learn e2e tests").clickAddButton();
        Assert.assertTrue(todosPage.verifySuccessToastMessage("Todo created successfully"));
    }

    @Test(description = "Free User Should not be Able to Edit Todo")
    void freeUserShouldNotBeAbleToEditTodo() {
        User user = UserFixtures.getDefaultUser();
        loginPage.load();
        loginPage.simulateLoginFreeUser(user);
        todosPage.addNewTodoComponent.addTodoApi(TodoFixture.generateTodo(), loginPage.getUserToken(user), false);
        todosPage.load();
        sideBarComponent.clickTodos();
        Assert.assertTrue(todosPage.loadTodos().get(0).isEditButtonLocked());
    }

    @Test(description = "User Should be Able to Delete Todo", groups = "e2e")
    void userShouldBeAbleToDeleteTodo() {
        User user = UserFixtures.getDefaultUser();
        loginPage.load();
        String token = loginPage.simulateLoginFreeUser(user);
        todosPage.addNewTodoComponent.addTodoApi(TodoFixture.generateTodo(), token, false);
        todosPage.load();
        todosPage.loadTodos().get(0).clickDeleteIcon();
        Assert.assertTrue(todosPage.verifySuccessToastMessage("Todo deleted successfully"));
    }


    @Test(description = "Advanced user should be able to add more than 3 todos", groups = "e2e")
    void advancedUserShouldBeAbleToAddMoreThan3Todos() {
        User user = UserFixtures.getDefaultUser();
        loginPage.load();
        String token = loginPage.simulateLoginAdvancedUser(user, CardFixture.getDummyCard());
        todosPage.addNewTodoComponent.addTodoApi(TodoFixture.generateTodo(), token, true);
        todosPage.load();
        todosPage.clickAddTodo();
        todosPage.addNewTodoComponent.fillTodoTitle("Read a book").clickAddButton();
        Assert.assertTrue(todosPage.verifySuccessToastMessage("Todo created successfully"));
    }

    @Test(description = "Advanced user should be able to edit todos", groups = "e2e")
    void advancedUserShouldBeAbleToEditTodo() {
        User user = UserFixtures.getDefaultUser();
        loginPage.load();
        String token = loginPage.simulateLoginAdvancedUser(user, CardFixture.getDummyCard());
        todosPage.addNewTodoComponent.addTodoApi(TodoFixture.generateTodo(), token, false);
        todosPage.load();
        todosPage.loadTodos().get(0).clickEditTodoButton();
        todosPage.editTodoComponent.editTodo("-edited").clickUpdateTodo();
        Assert.assertTrue(todosPage.verifySuccessToastMessage("Todo updated successfully"));
    }
}
