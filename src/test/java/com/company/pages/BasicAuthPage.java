package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.company.data.BasicAuthData;

/** Page Object для страницы Basic Auth */
public class BasicAuthPage extends BasePage {

    private static final By DISPLAY_IMAGE_BUTTON = By.id("displayImage");
    private static final By AUTH_IMAGE = By.id("downloadImg");

    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Регистрация данных для Basic Auth")
    public BasicAuthPage registerBasicAuth() {
        if (driver instanceof HasAuthentication) {
            ((HasAuthentication) driver)
                    .register(
                            UsernameAndPassword.of(BasicAuthData.USERNAME, BasicAuthData.PASSWORD));
        } else {
            throw new RuntimeException("Текущий драйвер не поддерживает HasAuthentication");
        }

        return this;
    }

    @Step("Нажатие на кнопку DISPLAY IMAGE")
    public BasicAuthPage clickDisplayImage() {
        clickElement(DISPLAY_IMAGE_BUTTON);

        return this;
    }

    @Step("Проверка успешной авторизации через атрибут 'src'")
    public boolean isAuthSuccessful() {
        WebElement image = findVisibleElement(AUTH_IMAGE);
        String src = image.getAttribute("src");

        return src != null && src.contains("authenticatedimage");
    }
}
