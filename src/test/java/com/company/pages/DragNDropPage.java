package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/** Page Object для страницы Drag and Drop */
public class DragNDropPage extends BasePage {

    private static final By IFRAME = By.xpath("//iframe[contains(@src, 'droppable/default.html')]");
    private static final By DRAGGABLE = By.id("draggable");
    private static final By DROPPABLE = By.id("droppable");
    private static final By DROPPABLE_TEXT = By.cssSelector("#droppable p");

    public DragNDropPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переключение в IFrame")
    public DragNDropPage switchToIframe() {
        WebElement iframe = findVisibleElement(IFRAME);
        driver.switchTo().frame(iframe);

        return this;
    }

    @Step("Переключение из IFrame")
    public DragNDropPage switchFromIframe() {
        driver.switchTo().defaultContent();

        return this;
    }

    @Step("Перетаскивание элемента")
    public DragNDropPage dragAndDrop() {
        WebElement draggable = findVisibleElement(DRAGGABLE);
        WebElement droppable = findVisibleElement(DROPPABLE);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable, droppable).perform();

        return this;
    }

    @Step("Получение текста из принимающего элемента")
    public String getDroppableText() {
        return findVisibleElement(DROPPABLE_TEXT).getText().strip();
    }
}
