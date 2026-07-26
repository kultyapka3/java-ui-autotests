package com.company.pages;

import java.time.Duration;
import java.util.List;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;

import com.company.config.Config;

/** Базовый Page Object */
public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.getDefaultTimeout()));
    }

    @Step("Открытие URL = {url}")
    public void open(String url) {
        driver.get(url);
    }

    @Step("Поиск видимого элемента по локатору = {locator}")
    public WebElement findVisibleElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Поиск видимых элементов по локатору = {locator}")
    public List<WebElement> findVisibleElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    @Step("Прокручивание страницы до середины")
    public void scrollPageToMiddle() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight / 2);");
    }

    @Step("Поиск кликабельного элемента c локатором = {locator}")
    public WebElement findClickableElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Step("Получение текущего URL")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Ввод текста ({text}) в элемент с локатором = {locator}")
    public void sendKeysToElement(By locator, String text) {
        WebElement element = findVisibleElement(locator);

        if (element.getAttribute("value") != null && !element.getAttribute("value").isEmpty()) {
            element.clear();
        }

        element.sendKeys(text);
    }

    @Step("Клик по элементу с локатором = {locator}")
    public void clickElement(By locator) {
        findClickableElement(locator).click();
    }

    @Step("Принятие алерта")
    public void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}
