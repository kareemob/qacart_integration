package com.qacart.tasky.components;

import com.qacart.tasky.client.TodoClient;
import com.qacart.tasky.models.Todo;
import com.qacart.tasky.utils.BrowserActionsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class AddNewTodoComponent {
    private final By todoTitleInput = By.cssSelector("input.MuiInputBase-input.MuiOutlinedInput-input.css-c3zido");
    private final By addButton = By.xpath("//button[text()='Add']");
    private final By cancelButton = By.xpath("//button[text()='Cancel']");
    private final By componentTitle = By.xpath("//h2[contains(text(),'Add New')]");


    private WebDriverWait wait(int timeout){
         return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }
    @Step("Fill todo title")
    public AddNewTodoComponent fillTodoTitle(String title){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(todoTitleInput)).click();
        try {
            getDriver().findElement(todoTitleInput).sendKeys(title);
        }catch (RuntimeException e){
        BrowserActionsUtils.sendKeysWithJS(todoTitleInput, title);
        }
        wait(5).until(ExpectedConditions.attributeToBeNotEmpty(getDriver().findElement(todoTitleInput), "value"));
        wait(5).until(ExpectedConditions.attributeToBe(getDriver().findElement(todoTitleInput), "value", title));
        return this;
    }
    @Step("Click add new todo")
    public void clickAddButton() {
        getDriver().findElement(addButton).click();
    }
    @Step("Is add new todo component is visible")
    public boolean isAddNewTodoComponentVisible() {
        List<WebElement> elements = getDriver().findElements(componentTitle);

        if (!elements.isEmpty()){
            return elements.get(0).isDisplayed();
        }else {
            return false;
        }
    }
    @Step("Click cancel todo")
    public void clickCancelButton() {
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(componentTitle));
        getDriver().findElement(cancelButton).click();
        wait(5).until(ExpectedConditions.invisibilityOfElementLocated(cancelButton));
    }
    @Step("Add todo using API")
    public void addTodoApi(Todo todo, String token, boolean isAdvanced) {
        if (isAdvanced) {
            for (int i = 0; i < 3; i++) { // Run 3 times for advanced users
                TodoClient.addTask(token, todo);
            }
        } else {
            TodoClient.addTask(token, todo); // Run once for non-advanced users
        }
    }

}
