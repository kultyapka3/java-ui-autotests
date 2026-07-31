package com.company.tests.cookie;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.config.Config;
import com.company.pages.SqlExPage;
import com.company.utils.CookieUtils;

/** ТК12. Авторизация на сайте, использующая Cookies */
@Epic("SQL-Ex")
@Feature("Cookies")
@Story("Авторизация на сайте, использующая Cookies")
@Severity(SeverityLevel.CRITICAL)
public class CookiesAuthTest extends BaseTest {

    @Test(
            description = "TК12.1. Авторизация на сайте, использующая Cookies",
            groups = {"sqlex", "cookies"})
    public void testFirstRunCookiesAuth() {
        SqlExPage sqlExPage = createSqlExPage();

        Allure.step(
                "Первичная авторизация",
                () -> {
                    sqlExPage
                            .enterLogin(Config.getSQLExLogin())
                            .enterPassword(Config.getSQLExPassword())
                            .clickLogin();

                    Assert.assertTrue(sqlExPage.isLoggedIn(), "Пользователь не авторизирован");
                });
        Allure.step(
                "Сохранение Cookies",
                () -> {
                    CookieUtils.saveCookies(driver, Config.getCookiesFilePath());
                });
    }

    @Test(
            description = "TК12.2. Авторизация на сайте, использующая Cookies",
            dependsOnMethods = "testFirstRunCookiesAuth",
            groups = {"sqlex", "cookies"})
    public void testSecondRunCookiesAuth() {
        SqlExPage sqlExPage = createSqlExPage();

        Allure.step(
                "Загрузка Cookies из файла",
                () -> {
                    CookieUtils.loadCookies(driver, Config.getCookiesFilePath());

                    Assert.assertTrue(
                            sqlExPage.isLoggedIn(),
                            "Пользователь не авторизирован с помощью Cookies");
                });

        sqlExPage.clickLogout();
    }
}
