package com.qacart.tasky.components;

import com.qacart.tasky.utils.BrowserActionsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qacart.tasky.driver.managers.DriverManager.getDriver;

public class TodoComponent {
    private final By deleteIcon = By.cssSelector("button.css-1ko9i2l");
    private final By editButton = By.cssSelector("[data-testid='EditIcon']");
    private final By editButtonLocked = By.cssSelector("span[aria-label='Upgrade to Advanced to edit todos']");
    private final By markAsCompleteCheckBox = By.cssSelector("[data-testid=CheckBoxOutlineBlankIcon]");

    private WebDriverWait wait(int timeout){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }

    @Step("Delete Todo")
    public void clickDeleteIcon() {
        getDriver().findElement(deleteIcon).click();
    }
    @Step("Click edit todo button")
    public void clickEditTodoButton(){
        wait(5).until(ExpectedConditions.visibilityOfElementLocated(editButton)).click();
    }
    @Step("Is edit button locked")
    public boolean isEditButtonLocked(){
        WebElement lockedEditButton = wait(4).until(ExpectedConditions.visibilityOfElementLocated(editButtonLocked));
        BrowserActionsUtils.actions().moveToElement(lockedEditButton).perform();
        return lockedEditButton.isDisplayed();
    }
    @Step("Wait until check box displayed")
    public void waitUntilCheckBoxIsDisplayed(){
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(markAsCompleteCheckBox)).isDisplayed();
    }
}
