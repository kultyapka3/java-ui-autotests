package com.company.tests.auth;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.data.LoginData;
import com.company.pages.LoginPage;

/** ТК06. Авторизация на сайте с валидными данными */
@Epic("Way2Automation")
@Feature("Авторизация")
@Story("Авторизация на сайте с валидными данными")
@Severity(SeverityLevel.CRITICAL)
public class LoginWithValidDataTest extends BaseTest {

    @Test(
            description = "TК06. Авторизация на сайте с валидными данными",
            groups = {"way2automation", "positive"})
    public void testLoginWithValidData() {
        LoginPage loginPage = createLoginPage();

        loginPage
                .enterUsernameLogin(LoginData.VALID_USERNAME_LOGIN)
                .enterPassword(LoginData.VALID_PASSWORD)
                .enterUsername(LoginData.VALID_USERNAME)
                .clickLogin();

        Allure.step(
                "Проверка успешной авторизации",
                () -> {
                    Assert.assertTrue(
                            loginPage.getSuccessMessage().contains("You're logged in!!"),
                            "Сообщение о успешной авторизации не найдено");
                });

        loginPage.clickLogout();
    }
}
