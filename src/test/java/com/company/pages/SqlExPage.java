package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SqlExPage extends BasePage {

    private static final By LOGIN_FIELD = By.name("login");
    private static final By PASSWORD_FIELD = By.name("psw");
    private static final By LOGIN_BUTTON = By.xpath("//input[@value='Вход']");
    private static final By LOGOUT_BUTTON = By.xpath("//img[@title='Выход...']");

    public SqlExPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод логина = {login}")
    public SqlExPage enterLogin(String login) {
        sendKeysToElement(LOGIN_FIELD, login);

        return this;
    }

    @Step("Ввод пароля = {password}")
    public SqlExPage enterPassword(String password) {
        sendKeysToElement(PASSWORD_FIELD, password);

        return this;
    }

    @Step("Нажатие на кнопку 'Вход'")
    public SqlExPage clickLogin() {
        clickElement(LOGIN_BUTTON);

        return this;
    }

    @Step("Проверка, что пользователь авторизован")
    public boolean isLoggedIn() {
        try {
            return findVisibleElement(LOGOUT_BUTTON).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Нажатие на кнопку выхода 'замок'")
    public SqlExPage clickLogout() {
        clickElement(LOGOUT_BUTTON);

        return this;
    }
}
