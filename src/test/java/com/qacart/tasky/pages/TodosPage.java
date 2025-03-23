package com.qacart.tasky.pages;

import com.qacart.tasky.components.AddNewTodoComponent;
import com.qacart.tasky.components.EditTodoComponent;
import com.qacart.tasky.components.TodoComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class TodosPage {
    private final By noTodosMessage = By.cssSelector(".MuiTypography-root.MuiTypography-body1.css-29if6k");
    private final By addTodoButtonIcon = By.cssSelector("[data-testid='AddIcon']");
    private final By addTodoButton = By.xpath("//button[normalize-space()='Add Todo']");
    private final By successToastMessage = By.cssSelector(".Toastify__toast--success");
    private final By todoItem = By.cssSelector(".MuiListItem-root.MuiListItem-gutters.MuiListItem-padding.MuiListItem-secondaryAction.css-1hkd1mz");
    private final By upgradeMessageAlert = By.xpath("//div[contains(text(),'Upgrade to Advanced to unlock todo editing!')]");
    private final By freeUserWarningTodoLimit = By.cssSelector("[data-testid=ReportProblemOutlinedIcon]");
    public final AddNewTodoComponent addNewTodoComponent = new AddNewTodoComponent();
    public final EditTodoComponent editTodoComponent = new EditTodoComponent();
    public final List<TodoComponent> todos = new ArrayList<>();

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    @Step("Is no todos displayed")
    public boolean isNoTodosMessageDisplayed(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(noTodosMessage)).isDisplayed();
    }
    @Step("Click add todo button")
    public void clickAddTodo(){
        wait(5).until(ExpectedConditions.elementToBeClickable(addTodoButtonIcon)).click();
    }
    @Step("Load todos")
    public List<TodoComponent> loadTodos(){
        List<WebElement> availableTodos = getDriver().findElements(todoItem);
        for (WebElement availableTodo : availableTodos){
            todos.add(new TodoComponent());
        }
        return this.todos;
    }
    @Step("Is free user warning displayed")
    public boolean isFreeUserWarningForTodoLimitDisplayed(){
        return wait(5).until(ExpectedConditions.visibilityOfElementLocated(freeUserWarningTodoLimit)).isDisplayed();
    }
    @Step("Is add button disabled")
    public boolean isAddButtonDisabled(){
        return !wait(5).until(ExpectedConditions.visibilityOfElementLocated(addTodoButton)).isEnabled();
    }
    @Step("Is upgrade message displayed")
    public boolean isUpgradeMessageDisplayed(){
        return getDriver().findElement(upgradeMessageAlert).isDisplayed();
    }
    @Step("Are free alerts displayed")
    public boolean areFreeAlertsNotDisplayed() {
        return getDriver().findElements(freeUserWarningTodoLimit).isEmpty() && getDriver().findElements(upgradeMessageAlert).isEmpty() && getDriver().findElement(addTodoButtonIcon).isEnabled();

    }
    @Step("Is success message displayed")
    public boolean verifySuccessToastMessage(String messageText){
        return getDriver().findElements(successToastMessage)
                .stream()
                .anyMatch(toast -> toast.getAttribute("innerText").contains(messageText));
    }
}
