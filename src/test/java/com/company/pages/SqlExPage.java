package com.company.pages;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.company.config.Config;
import com.company.utils.CookieUtils;

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

    @Step("Авторизация с помощью логина/пароля или через Cookies")
    public SqlExPage smartLogin() {
        String cookiesFile = Config.getCookiesFilePath();

        if (Files.exists(Paths.get(cookiesFile))) {
            CookieUtils.loadCookies(driver, cookiesFile);
        } else {
            enterLogin(Config.getSQLExLogin())
                    .enterPassword(Config.getSQLExPassword())
                    .clickLogin();

            CookieUtils.saveCookies(driver, cookiesFile);
        }

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

    @Step("Удаление фокуса с поля логина")
    public void removeFocusFromLogin() {
        removeFocusFromElement(LOGIN_FIELD);
    }

    @Step("Проверка активности поля логина")
    public boolean isLoginFieldActive() {
        return isElementActive(LOGIN_FIELD);
    }

    @Step("Проверка наличия вертикального скролла SQL-Ex")
    public boolean hasPageVerticalScroll() {
        return hasVerticalScroll();
    }

    @Step("Прокрутка страницы SQL-Ex вниз")
    public void scrollPageToBottom() {
        scrollToBottom();
    }

    @Step("Проверка, что страница SQL-Ex прокручена до конца")
    public boolean isPageAtBottom() {
        return isAtBottom();
    }
}
