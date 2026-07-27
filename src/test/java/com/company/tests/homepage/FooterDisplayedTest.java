package com.company.tests.homepage;

import java.util.List;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.HomePage;

/** ТК02. Отображение информации и навигации в футере на главной странице */
@Epic("Way2Automation")
@Feature("Отображение элементов")
@Story("Отображение информации и навигации в футере на главной странице")
@Severity(SeverityLevel.NORMAL)
public class FooterDisplayedTest extends BaseTest {

    @Test(
            description = "TC02. Отображение информации и навигации в футере на главной странице",
            groups = {"way2automation", "positive"})
    public void testFooterDisplayed() {
        HomePage homePage = createHomePage();

        List<String> footerColumns = homePage.getFooterNavigationColumns();

        Allure.step(
                "Проверка содержимого футера",
                () -> {
                    Assert.assertTrue(
                            homePage.getFooterInformation().contains("India's leading software"),
                            "Информация в футере не совпадает ожидаемой");
                    Assert.assertTrue(
                            footerColumns.contains("Courses")
                                    && footerColumns.contains("Company")
                                    && footerColumns.contains("Support"),
                            "Навигация в футере не совпадает с ожидаемой");
                });
    }
}
