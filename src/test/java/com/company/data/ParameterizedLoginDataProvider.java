package com.company.data;

import org.testng.annotations.DataProvider;

/** DataProvider класс для авторизации */
public class ParameterizedLoginDataProvider {

    @DataProvider(name = "loginScenarios")
    public static Object[][] getLoginScenarios() {
        return new Object[][] {
            // testName, usernameLogin, password, username, loginDisabled, expectedSuccess,
            // expectedError
            {"Валидные данные", "angular", "password", "Hero", false, "You're logged in!!", null},
            {
                "Неверный логин",
                "invalid",
                "password",
                "Hero",
                false,
                null,
                "Username or password is incorrect"
            },
            {
                "Неверный пароль",
                "angular",
                "invalid",
                "Hero",
                false,
                null,
                "Username or password is incorrect"
            },
            {"Пустой логин", "", "password", "Hero", true, null, null},
            {"Пустой пароль", "angular", "", "Hero", true, null, null},
            {"Пробелы в полях", " ", " ", "Hero", true, null, null}
        };
    }
}
