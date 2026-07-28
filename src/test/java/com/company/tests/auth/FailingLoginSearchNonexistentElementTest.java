package com.company.tests.auth;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.data.LoginData;
import com.company.pages.LoginPage;

/** ТК11. Падающий тест авторизации с отсутствующим элементом страницы */
@Epic("Way2Automation")
@Feature("Авторизация")
@Story("Падающий тест авторизации с отсутствующим элементом страницы")
@Severity(SeverityLevel.NORMAL)
public class FailingLoginSearchNonexistentElementTest extends BaseTest {

    @Test(
            description = "ТК11. Падающий тест авторизации с отсутствующим элементом страницы",
            groups = {"way2automation", "failing"})
    public void testFailingLoginSearchNonexistentElement() {
        LoginPage loginPage = createLoginPage();

        loginPage
                .enterUsernameLogin(LoginData.VALID_USERNAME_LOGIN)
                .enterPassword(LoginData.VALID_PASSWORD)
                .enterUsername(LoginData.VALID_USERNAME)
                .clickLogin();

        Allure.step(
                "Проверка ошибки авторизации",
                () -> {
                    // временно для теста
                    Assert.assertTrue((Boolean) false, "Сообщение об ошибке не найдено");
                });

        loginPage.clickLogout();
    }
}
