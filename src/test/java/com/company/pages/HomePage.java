package com.company.pages;

import java.util.List;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Page Object главной страницы */
public class HomePage extends BasePage {

    private static final By HEADER = By.cssSelector("header.site-header");
    private static final By NAVIGATION = By.cssSelector("nav.nav-links#navLinks");
    private static final By COURSES_BLOCK =
            By.xpath("//span[text()='Popular Courses']/ancestor::section[1]");
    private static final By FOOTER = By.cssSelector("footer.site-footer");
    private static final By FOOTER_INFO = By.cssSelector("footer.site-footer p");
    private static final By FOOTER_NAV = By.cssSelector("footer .footer-col h4");
    private static final By LIFETIME_MEMBERSHIP_LINK = By.linkText("Lifetime Membership");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /** Проверка видимости хедера */
    @Step("Проверка видимости хедера")
    public boolean isHeaderVisible() {
        return findVisibleElement(HEADER).isDisplayed();
    }

    /** Проверка видимости навигации */
    @Step("Проверка видимости навигации")
    public boolean isNavigationVisible() {
        return findVisibleElement(NAVIGATION).isDisplayed();
    }

    /** Проверка видимости блока курсов */
    @Step("Проверка видимости блока курсов")
    public boolean isCoursesBlockVisible() {
        return findVisibleElement(COURSES_BLOCK).isDisplayed();
    }

    /** Проверка видимости футера */
    @Step("Проверка видимости футера")
    public boolean isFooterVisible() {
        return findVisibleElement(FOOTER).isDisplayed();
    }

    /** Получение текста из блока информации в футере */
    @Step("Получение текста из блока информации в футере")
    public String getFooterInformation() {
        WebElement footerInfoElement = findVisibleElement(FOOTER_INFO);

        return footerInfoElement.getText().strip();
    }

    /** Получение названий колонок навигации в футере */
    @Step("Получение названий колонок навигации в футере")
    public List<String> getFooterNavigationColumns() {
        List<WebElement> footerColumns = findVisibleElements(FOOTER_NAV);

        return footerColumns.stream().map(WebElement::getText).toList();
    }

    /** Проверка фиксации меню при прокрутке */
    @Step("Проверка фиксации меню при прокрутке")
    public boolean isNavigationSticky() {
        scrollPageToMiddle();

        return isNavigationVisible();
    }

    /** Переход на страницу Lifetime Membership */
    @Step("Переход на страницу Lifetime Membership")
    public LifetimeMembershipPage goToLifetimeMembership() {
        findClickableElement(LIFETIME_MEMBERSHIP_LINK).click();

        return new LifetimeMembershipPage(driver);
    }
}
