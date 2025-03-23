package com.qacart.tasky.components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class EditTodoComponent {
    private final By todoTitleInput = By.cssSelector(".MuiInputBase-input");
    private final By updateButton = By.xpath("//button[text()='Update']");

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    public EditTodoComponent editTodo(String newTitle){
        wait(3).until(ExpectedConditions.elementToBeClickable(todoTitleInput)).sendKeys(newTitle);
        return this;
    }

    public void clickUpdateTodo(){
        getDriver().findElement(updateButton).click();
    }
}
