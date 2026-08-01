package com.company.tests.jsexecutor;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.config.Config;
import com.company.pages.SqlExPage;

/** ТК13. Проверка работы JavaScriptExecutor */
@Epic("SQL-Ex")
@Feature("JS Executor")
@Story("Проверка работы JavaScriptExecutor")
@Severity(SeverityLevel.NORMAL)
public class JsExecutorTest extends BaseTest {

    @Test(
            description = "ТК13. Проверка работы JavaScriptExecutor",
            groups = {"sqlex", "jsexecutor"})
    public void testJsExecutor() {
        SqlExPage sqlExPage = createSqlExPage();
        sqlExPage.enterLogin(Config.getSQLExLogin());

        Allure.step(
                "Проверка удаления фокуса с поля логина",
                () -> {
                    sqlExPage.removeFocusFromLogin();
                    Assert.assertFalse(
                            sqlExPage.isLoginFieldActive(),
                            "Фокус должен быть убран с поля логина");
                });

        Allure.step(
                "Работа с вертикальным скроллом",
                () -> {
                    Assert.assertTrue(
                            sqlExPage.hasPageVerticalScroll(),
                            "На странице должен присутствовать вертикальный скролл");

                    sqlExPage.scrollPageToBottom();

                    Assert.assertTrue(
                            sqlExPage.isPageAtBottom(),
                            "Страница должна быть прокручена до самого низа");
                });
    }
}
