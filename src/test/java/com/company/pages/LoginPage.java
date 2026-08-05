package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Page Object страницы авторизации */
public class LoginPage extends BasePage {

    private static final By USERNAME_LOGIN_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By USERNAME_FIELD =
            By.xpath("//div[contains(@class, \"formly-field-input\")]//input[@required]");
    private static final By LOGIN_BUTTON = By.cssSelector("button[ng-click='Auth.login()']");
    private static final By SUCCESS_MESSAGE =
            By.xpath("//p[contains(text(), \"You're logged in!!\")]");
    private static final By ERROR_MESSAGE = By.cssSelector(".alert.alert-danger");
    private static final By LOGOUT_BUTTON = By.linkText("Logout");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод логина = {usernameLogin}")
    public LoginPage enterUsernameLogin(String usernameLogin) {
        sendKeysToElement(USERNAME_LOGIN_FIELD, usernameLogin);

        return this;
    }

    @Step("Ввод пароля = {password}")
    public LoginPage enterPassword(String password) {
        sendKeysToElement(PASSWORD_FIELD, password);

        return this;
    }

    @Step("Ввод имени = {username}")
    public LoginPage enterUsername(String username) {
        sendKeysToElement(USERNAME_FIELD, username);

        return this;
    }

    @Step("Получение состояния кнопки 'Login'")
    public boolean isLoginButtonEnabled() {
        return findVisibleElement(LOGIN_BUTTON).isEnabled();
    }

    @Step("Нажатие на кнопку 'Login'")
    public LoginPage clickLogin() {
        clickElement(LOGIN_BUTTON);

        return this;
    }

    @Step("Получение сообщения о успешной авторизации")
    public String getSuccessMessage() {
        return findVisibleElement(SUCCESS_MESSAGE).getText().strip();
    }

    @Step("Получение сообщения об ошибке авторизации")
    public String getErrorMessage() {
        return findVisibleElement(ERROR_MESSAGE).getText().strip();
    }

    @Step("Проверка наличия сообщения об ошибке на странице")
    public boolean isErrorMessagePresent() {
        return !driver.findElements(ERROR_MESSAGE).isEmpty();
    }

    @Step("Нажатие на кнопку 'Logout'")
    public LoginPage clickLogout() {
        clickElement(LOGOUT_BUTTON);

        return this;
    }
}
