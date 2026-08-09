package com.company.tests.tabs;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.FramesNWindowsPage;

/** ТК15. Проверка работы с несколькими вкладками */
@Epic("Way2Automation")
@Feature("Frames and Windows")
@Story("Проверка работы с несколькими вкладками")
@Severity(SeverityLevel.CRITICAL)
public class TabsTest extends BaseTest {

    @Test(
            description = "ТК15. Проверка работы с несколькими вкладками",
            groups = {"way2automation", "tabs"})
    public void testTabs() {
        FramesNWindowsPage framesNWindowsPage = createFramesNWindowsPage();

        framesNWindowsPage.switchToIFrame();
        String originalTab = framesNWindowsPage.getCurrentHandle();

        Allure.step(
                "Открытие второй вкладки и переключение фокуса на неё",
                () -> {
                    framesNWindowsPage.openNewTab().switchToNewTab(originalTab);
                });

        Allure.step(
                "Открытие третьей вкладки",
                () -> {
                    framesNWindowsPage.openNewTab();
                });

        Allure.step(
                "Проверка количества открытых вкладок",
                () -> {
                    int tabCount = framesNWindowsPage.getWindowCount();
                    Assert.assertEquals(
                            tabCount,
                            3,
                            String.format("Количество вкладок равно %d, а ожидалось 3", tabCount));
                });

        framesNWindowsPage.closeAllTabsExceptOriginal(originalTab).switchToOriginalTab(originalTab);
        framesNWindowsPage.switchFromIFrame();
    }
}
