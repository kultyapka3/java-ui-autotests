package com.company.tests.homepage;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.HomePage;

/** ТК03. Фиксация меню навигации при прокрутке страницы */
@Epic("Way2Automation")
@Feature("Навигация")
@Story("Фиксация меню навигации при прокрутке страницы")
@Severity(SeverityLevel.NORMAL)
public class NavigationVisibilityAfterScrollTest extends BaseTest {

    @Test(
            description = "TК03. Фиксация меню навигации при прокрутке страницы",
            groups = {"way2automation", "positive"})
    public void testNavigationVisibilityAfterScroll() {
        HomePage homePage = createHomePage();

        Allure.step(
                "Проверка видимости навигации после скролла",
                () -> {
                    Assert.assertTrue(
                            homePage.isNavigationSticky(),
                            "Меню навигации не отображается после прокрутки страницы до середины");
                });
    }
}
