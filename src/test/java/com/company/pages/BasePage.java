package com.company.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

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

    @Step("Удаление фокуса с элемента с локатором = {locator}")
    public void removeFocusFromElement(By locator) {
        WebElement element = findVisibleElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].blur();", element);
    }

    @Step("Проверка активности элемента")
    public boolean isElementActive(By locator) {
        WebElement element = findVisibleElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return (boolean)
                js.executeScript("return document.activeElement === arguments[0];", element);
    }

    @Step("Проверка наличия вертикального скролла")
    public boolean hasVerticalScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return (boolean)
                js.executeScript(
                        "return document.documentElement.scrollHeight > document.documentElement.clientHeight;");
    }

    @Step("Прокрутка страницы в самый низ через JavaScript")
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
    }

    @Step("Проверка достижения конца страницы")
    public boolean isAtBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return (boolean)
                js.executeScript(
                        "return (document.documentElement.scrollTop + document.documentElement.clientHeight) >= (document.documentElement.scrollHeight - 5);");
    }

    @Step("Переключение на новую вкладку")
    public void switchToNewTab(String originalHandle) {
        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    @Step("Возврат к исходной вкладке")
    public void switchToOriginalTab(String originalHandle) {
        driver.switchTo().window(originalHandle);
    }

    @Step("Закрытие всех вкладок, кроме исходной")
    public void closeAllTabsExceptOriginal(String originalHandle) {
        Set<String> allHandles = driver.getWindowHandles();

        for (String handle : allHandles) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    @Step("Получение дескриптора текущей вкладки")
    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    @Step("Получение количества открытых вкладок")
    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }

    @Step("Ввод в алерт текста = {text}")
    public void enterTextToAlert(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
    }
}
