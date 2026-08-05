package com.company.tests.cookie;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.SqlExPage;

/** ТК12. Авторизация на сайте через Cookies */
@Epic("SQL-Ex")
@Feature("Cookies")
@Story("Авторизация на сайте через Cookies")
@Severity(SeverityLevel.CRITICAL)
public class CookiesAuthTest extends BaseTest {

    @Test(
            description = "TК12. Авторизация на сайте через Cookies",
            groups = {"sqlex", "cookies"})
    public void testCookiesAuth() {
        SqlExPage sqlExPage = createSqlExPage();

        Allure.step(
                "Авторизация",
                () -> {
                    sqlExPage.smartLogin();

                    Assert.assertTrue(sqlExPage.isLoggedIn(), "Пользователь не авторизован");
                });

        sqlExPage.clickLogout();
    }
}
