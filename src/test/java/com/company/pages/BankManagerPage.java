package com.company.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Page Object страницы Bank Manager Login */
public class BankManagerPage extends BasePage {

    private static final By ADD_CUSTOMER_TAB_BTN =
            By.xpath("//button[contains(text(), 'Add Customer')]");
    private static final By FIRST_NAME_INPUT = By.cssSelector("input[ng-model='fName']");
    private static final By LAST_NAME_INPUT = By.cssSelector("input[ng-model='lName']");
    private static final By POST_CODE_INPUT = By.cssSelector("input[ng-model='postCd']");
    private static final By ADD_CUSTOMER_SUBMIT_BTN =
            By.xpath("//form//button[contains(text(), 'Add Customer')]");
    private static final By CUSTOMERS_TAB_BTN = By.xpath("//button[contains(text(), 'Customers')]");
    private static final By SEARCH_INPUT = By.cssSelector("input[ng-model='searchCustomer']");

    public BankManagerPage(WebDriver driver) {
        super(driver);
    }

    /** Переход на вкладку 'Add Customer' */
    @Step("Переход на вкладку 'Add Customer'")
    public BankManagerPage goToAddCustomerTab() {
        clickElement(ADD_CUSTOMER_TAB_BTN);

        return this;
    }

    /** Ввод имени */
    @Step("Ввод имени = {firstName}")
    public BankManagerPage enterFirstName(String firstName) {
        sendKeysToElement(FIRST_NAME_INPUT, firstName);

        return this;
    }

    /** Ввод фамилии */
    @Step("Ввод фамилии = {lastName}")
    public BankManagerPage enterLastName(String lastName) {
        sendKeysToElement(LAST_NAME_INPUT, lastName);

        return this;
    }

    /** Ввод почтового индекса */
    @Step("Ввод почтового индекса = {postCode}")
    public BankManagerPage enterPostCode(String postCode) {
        sendKeysToElement(POST_CODE_INPUT, postCode);

        return this;
    }

    /** Нажатие на кнопку 'Add Customer' */
    @Step("Нажатие на кнопку 'Add Customer'")
    public BankManagerPage addCustomer() {
        clickElement(ADD_CUSTOMER_SUBMIT_BTN);

        return this;
    }

    /** Закрытие всплывающего алерта о подтверждении добавления клиента */
    @Step("Закрытие всплывающего алерта о подтверждении добавления клиента")
    public BankManagerPage acceptCustomerAlert() {
        acceptAlert();

        return this;
    }

    /** Переход на вкладку 'Customers' */
    @Step("Переход на вкладку 'Customers'")
    public BankManagerPage goToCustomersTab() {
        clickElement(CUSTOMERS_TAB_BTN);

        return this;
    }

    /** Поиск по имени */
    @Step("Поиск по имени = {firstName}")
    public BankManagerPage searchCustomer(String firstName) {
        sendKeysToElement(SEARCH_INPUT, firstName);

        return this;
    }

    /** Удаление по имени */
    @Step("Удаление по имени = {firstName}")
    public BankManagerPage deleteCustomer(String firstName) {
        By deleteBtn =
                By.xpath(
                        "//td[contains(text(), '"
                                + firstName
                                + "')]/following-sibling::td/button[contains(text(), 'Delete')]");
        clickElement(deleteBtn);

        return this;
    }

    /** Очистка поля поиска */
    @Step("Очистка поля поиска")
    public BankManagerPage clearSearch() {
        WebElement searchField = findVisibleElement(SEARCH_INPUT);
        searchField.clear();

        return this;
    }

    /** Проверка наличия покупателя в таблице по имени */
    @Step("Проверка наличия покупателя в таблице по имени = {firstName}")
    public boolean isCustomerPresent(String firstName) {
        By customerRow = By.xpath("//td[contains(text(), '" + firstName + "')]");

        return !driver.findElements(customerRow).isEmpty();
    }
}
