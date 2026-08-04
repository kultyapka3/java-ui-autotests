package com.company.tests.cookie;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.SkipException;

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
            description = "TК12.1. Авторизация на сайте, сохраняющая Cookies",
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

                    Assert.assertTrue(
                            Files.exists(Paths.get(Config.getCookiesFilePath())),
                            "Файл с cookies не был создан после сохранения");
                });
    }

    @Test(
            description = "TК12.2. Авторизация на сайте, использующая Cookies",
            dependsOnMethods = "testFirstRunCookiesAuth",
            groups = {"sqlex", "cookies"})
    public void testSecondRunCookiesAuth() {
        if (!Files.exists(Paths.get(Config.getCookiesFilePath()))) {
            throw new SkipException(
                    "Файл cookies не найден\nСначала выполните тест testFirstRunCookiesAuth");
        }

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
