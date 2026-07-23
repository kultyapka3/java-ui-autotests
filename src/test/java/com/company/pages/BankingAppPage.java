package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Page Object навигационной страницы */
public class BankingAppPage extends BasePage {

    private static final By SAMPLE_FORM_LINK = By.cssSelector("a[href*='registrationform.html']");
    private static final By BANK_MANAGER_BTN =
            By.xpath("//button[contains(text(), 'Bank Manager Login')]");

    public BankingAppPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переход на страницу Sample Form")
    public SampleFormPage goToSampleForm() {
        clickElement(SAMPLE_FORM_LINK);

        return new SampleFormPage(driver);
    }

    @Step("Переход на страницу Bank Manager")
    public BankManagerPage goToBankManager() {
        clickElement(BANK_MANAGER_BTN);

        return new BankManagerPage(driver);
    }
}
