package com.company.tests.auth;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.LoginPage;

/** ТК09. Параметризованная авторизация */
@Epic("Way2Automation")
@Feature("Авторизация")
@Story("Параметризованная авторизация")
@Severity(SeverityLevel.CRITICAL)
public class ParameterizedLoginTest extends BaseTest {

    // testName, usernameLogin, password, username
    @DataProvider(name = "successLoginData")
    public Object[][] successLoginData() {
        return new Object[][] {{"Валидные данные", "angular", "password", "Hero"}};
    }

    @DataProvider(name = "failedLoginData")
    public Object[][] failedLoginData() {
        return new Object[][] {
            {"Неверный логин", "invalid", "password", "Hero"},
            {"Неверный пароль", "angular", "invalid", "Hero"}
        };
    }

    @DataProvider(name = "disabledButtonData")
    public Object[][] disabledButtonData() {
        return new Object[][] {
            {"Пустой логин", "", "password", "Hero"},
            {"Пустой пароль", "angular", "", "Hero"},
            {"Пробелы в полях", " ", " ", "Hero"}
        };
    }

    @Test(
            description = "TК09.1. Параметризованная авторизация (валидные данные)",
            dataProvider = "successLoginData",
            groups = {"way2automation", "parameterized"})
    public void testSuccessfulLogin(
            String testName, String usernameLogin, String password, String username) {
        LoginPage loginPage = createLoginPage();

        Allure.step(
                "Проверка сценария: " + testName,
                () -> {
                    loginPage
                            .enterUsernameLogin(usernameLogin)
                            .enterPassword(password)
                            .enterUsername(username)
                            .clickLogin();

                    Assert.assertTrue(
                            loginPage.getSuccessMessage().contains("You're logged in!!"),
                            "Сообщение об успешной авторизации не найдено");

                    loginPage.clickLogout();
                });
    }

    @Test(
            description = "TК09.2. Параметризованная авторизация (невалидные данные)",
            dataProvider = "failedLoginData",
            groups = {"way2automation", "parameterized"})
    public void testFailedLogin(
            String testName, String usernameLogin, String password, String username) {
        LoginPage loginPage = createLoginPage();

        Allure.step(
                "Проверка сценария: " + testName,
                () -> {
                    loginPage
                            .enterUsernameLogin(usernameLogin)
                            .enterPassword(password)
                            .enterUsername(username)
                            .clickLogin();

                    Assert.assertTrue(
                            loginPage
                                    .getErrorMessage()
                                    .contains("Username or password is incorrect"),
                            "Сообщение об ошибке не найдено");
                });
    }

    @Test(
            description = "TК09.3. Параметризованная авторизация (пустые поля)",
            dataProvider = "disabledButtonData",
            groups = {"way2automation", "parameterized"})
    public void testLoginButtonDisabled(
            String testName, String usernameLogin, String password, String username) {
        LoginPage loginPage = createLoginPage();

        Allure.step(
                "Проверка сценария: " + testName,
                () -> {
                    loginPage
                            .enterUsernameLogin(usernameLogin)
                            .enterPassword(password)
                            .enterUsername(username);

                    Assert.assertFalse(
                            loginPage.isLoginButtonEnabled(),
                            "Кнопка 'Login' должна быть неактивна");
                });
    }
}
