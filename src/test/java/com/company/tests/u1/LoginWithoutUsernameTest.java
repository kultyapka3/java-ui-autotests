package com.company.tests.u1;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.LoginPage;
import com.company.data.LoginData;

/** ТК05. Попытка авторизации с незаполненным полем Username * */
@Epic("Way2Automation")
@Feature("Авторизация")
@Story("Попытка авторизации с незаполненным полем 'Username *'")
@Severity(SeverityLevel.CRITICAL)
public class LoginWithoutUsernameTest extends BaseTest {

    @Test(
            description = "TC05. Попытка авторизации с незаполненным полем 'Username *'",
            groups = {"way2automation", "negative"})
    public void testLoginWithoutUsername() {
        LoginPage loginPage = createLoginPage();

        loginPage
                .enterUsernameLogin(LoginData.VALID_USERNAME_LOGIN)
                .enterPassword(LoginData.VALID_PASSWORD);

        Allure.step(
                "Проверка состояния кнопки 'Login'",
                () -> {
                    Assert.assertFalse(
                            loginPage.isLoginButtonEnabled(),
                            "Кнопка 'Login' должна быть неактивна");
                });
    }
}
