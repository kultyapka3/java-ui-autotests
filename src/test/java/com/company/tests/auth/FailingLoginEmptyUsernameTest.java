package com.company.tests.auth;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.LoginPage;
import com.company.data.LoginData;

/** ТК10. Падающий тест авторизации с пустым 'Username *' */
@Epic("Way2Automation")
@Feature("Авторизация")
@Story("Падающий тест авторизации с пустым 'Username *'")
@Severity(SeverityLevel.NORMAL)
public class FailingLoginEmptyUsernameTest extends BaseTest {

    @Test(
            description = "TК10. Падающий тест авторизации с пустым 'Username *'",
            groups = {"way2automation", "failing"})
    public void testFailingLoginEmptyUsername() {
        LoginPage loginPage = createLoginPage();

        loginPage
                .enterUsernameLogin(LoginData.VALID_USERNAME_LOGIN)
                .enterPassword(LoginData.VALID_PASSWORD);

        Allure.step(
                "Попытка нажатия кнопки 'Login'",
                () -> {
                    loginPage.clickLogin();

                    Assert.assertTrue(
                            loginPage
                                    .getErrorMessage()
                                    .contains("Username or password is incorrect"),
                            "Сообщение об ошибке не найдено");
                });
    }
}
