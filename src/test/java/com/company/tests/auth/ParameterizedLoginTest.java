package com.company.tests.auth;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.data.ParameterizedLoginDataProvider;
import com.company.pages.LoginPage;

/** ТК09. Параметризованная авторизация */
@Epic("Way2Automation")
@Feature("Авторизация")
@Story("Параметризованная авторизация")
@Severity(SeverityLevel.CRITICAL)
public class ParameterizedLoginTest extends BaseTest {

    @Test(
            description = "TК09. Параметризованная авторизация",
            dataProvider = "loginScenarios",
            dataProviderClass = ParameterizedLoginDataProvider.class,
            groups = {"way2automation", "positive", "parameterized"})
    @Story("Сценарий: {testName}")
    public void testParameterizedLogin(
            String testName,
            String usernameLogin,
            String password,
            String username,
            boolean isButtonDisabled,
            String expectedSuccess,
            String expectedError) {
        LoginPage loginPage = createLoginPage();

        Allure.step(
                "Проверка сценария: " + testName,
                () -> {
                    loginPage
                            .enterUsernameLogin(usernameLogin)
                            .enterPassword(password)
                            .enterUsername(username);

                    if (isButtonDisabled) {
                        Assert.assertFalse(
                                loginPage.isLoginButtonEnabled(),
                                String.format(
                                        "Кнопка 'Login' должны быть заблокирована в сценарии (%s)",
                                        testName));
                    } else {
                        loginPage.clickLogin();

                        if (expectedSuccess != null) {
                            Assert.assertTrue(
                                    loginPage.getSuccessMessage().contains("You're logged in!!"),
                                    String.format(
                                            "Сообщение о успешной авторизации не найдено (%s)",
                                            expectedSuccess));

                            loginPage.clickLogout();
                        }

                        if (expectedError != null) {
                            Assert.assertTrue(
                                    loginPage
                                            .getErrorMessage()
                                            .contains("Username or password is incorrect"),
                                    String.format(
                                            "Сообщение об ошибке не найдено (%s)", expectedError));
                        }
                    }
                });
    }
}
