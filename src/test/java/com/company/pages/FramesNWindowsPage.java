package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Page Object для страницы Frames and Windows */
public class FramesNWindowsPage extends BasePage {

    private static final By IFRAME =
            By.xpath("//iframe[contains(@src, 'frames-windows/defult1.html')]");
    private static final By NEW_TAB_LINK = By.linkText("New Browser Tab");

    public FramesNWindowsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переключение в IFrame")
    public FramesNWindowsPage switchToIFrame() {
        WebElement iframe = findVisibleElement(IFRAME);
        driver.switchTo().frame(iframe);

        return this;
    }

    @Step("Переключение из IFrame")
    public FramesNWindowsPage switchFromIFrame() {
        driver.switchTo().defaultContent();

        return this;
    }

    @Step("Сохранение дескриптора текущей вкладки")
    public String getCurrentHandle() {
        return getCurrentWindowHandle().strip();
    }

    @Step("Нажатие на ссылку открытия новой вкладки")
    public FramesNWindowsPage openNewTab() {
        clickElement(NEW_TAB_LINK);

        return this;
    }

    @Step("Переключение на новую вкладку Frames and Windows")
    public FramesNWindowsPage switchToNewFramesTab(String originalHandle) {
        switchToNewTab(originalHandle);

        return this;
    }

    @Step("Возврат к исходной вкладке Frames and Windows")
    public FramesNWindowsPage switchToOriginalFramesTab(String originalHandle) {
        switchToOriginalTab(originalHandle);

        return this;
    }

    @Step("Закрытие всех вкладок Frames and Windows, кроме исходной")
    public FramesNWindowsPage closeAllFramesTabsExceptOriginal(String originalHandle) {
        closeAllTabsExceptOriginal(originalHandle);

        return this;
    }

    @Step("Получение количества открытых вкладок Frames and Windows")
    public int getFramesWindowCount() {
        return getWindowCount();
    }
}
