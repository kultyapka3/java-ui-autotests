package com.company.tests.homepage;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.HomePage;

/** ТК01. Отображение основных элементов на главной странице */
@Epic("Way2Automation")
@Feature("Отображение элементов")
@Story("Отображение основных элементов на главной странице")
@Severity(SeverityLevel.CRITICAL)
public class MainElementsDisplayedTest extends BaseTest {

    @Test(
            description = "TК01. Отображение основных элементов на главной странице",
            groups = {"way2automation", "positive"})
    public void testMainElementsDisplayed() {
        HomePage homePage = createHomePage();

        Allure.step(
                "Проверка отображения элементов на главной странице",
                () -> {
                    Assert.assertTrue(homePage.isHeaderVisible(), "Хедер не отображается");
                    Assert.assertTrue(homePage.isNavigationVisible(), "Навигация не отображается");
                    Assert.assertTrue(
                            homePage.isCoursesBlockVisible(), "Блок курсов не отображается");
                    Assert.assertTrue(homePage.isFooterVisible(), "Футер не отображается");
                });
    }
}
