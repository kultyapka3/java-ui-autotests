package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Page Object страницы Lifetime Membership */
public class LifetimeMembershipPage extends BasePage {

    private static final By LIFETIME_MEMBERSHIP_TITLE = By.cssSelector("h1");

    public LifetimeMembershipPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение URL страницы")
    public String getLifetimeMembershipUrl() {
        return getCurrentUrl();
    }

    @Step("Получение заголовка страницы")
    public String getLifetimeMembershipTitle() {
        return findVisibleElement(LIFETIME_MEMBERSHIP_TITLE).getText().strip();
    }
}
