package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Page Object для страницы Alert */
public class AlertPage extends BasePage {

    private static final By INPUT_ALERT_BUTTON = By.xpath("//a[text()='Input Alert']");
    private static final By IFRAME = By.xpath("//iframe[contains(@src, 'input-alert.html')]");
    private static final By DEMONSTRATE_ALERT_BUTTON =
            By.xpath("//button[contains(., 'Click the button to demonstrate the Input box')]");
    private static final By ALERT_RESULT = By.id("demo");

    public AlertPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переключение в IFrame")
    public AlertPage switchToIFrame() {
        WebElement iframe = findVisibleElement(IFRAME);
        driver.switchTo().frame(iframe);

        return this;
    }

    @Step("Переключение из IFrame")
    public AlertPage switchFromIFrame() {
        driver.switchTo().defaultContent();

        return this;
    }

    @Step("Нажатие на кнопку переключения на INPUT ALERT")
    public AlertPage clickSwitchToInputAlert() {
        clickElement(INPUT_ALERT_BUTTON);

        return this;
    }

    @Step("Нажатие на кнопку вызова алерта")
    public AlertPage clickDemonstrateAlert() {
        clickElement(DEMONSTRATE_ALERT_BUTTON);

        return this;
    }

    @Step("Заполнение алерта тестом = {alertText}")
    public AlertPage enterAlert(String alertText) {
        enterTextToAlert(alertText);

        return this;
    }

    @Step("Подтверждение алерта")
    public AlertPage acceptInputAlert() {
        acceptAlert();

        return this;
    }

    @Step("Получение результата ввода текста в алерт")
    public String getAlertResultText() {
        return findVisibleElement(ALERT_RESULT).getText().strip();
    }
}
