package com.company.pages;

import java.util.List;
import java.util.stream.Collectors;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Page Object страницы регистрации Sample Form */
public class SampleFormPage extends BasePage {

    private static final By FIRST_NAME = By.id("firstName");
    private static final By LAST_NAME = By.id("lastName");
    private static final By EMAIL = By.id("email");
    private static final By PASSWORD = By.id("password");
    private static final By HOBBIES_LABELS = By.cssSelector(".checkbox-group label");
    private static final By GENDER_DROPDOWN = By.id("gender");
    private static final By ABOUT_TEXTAREA = By.id("about");
    private static final By REGISTER_BUTTON = By.cssSelector("button[type='submit']");
    private static final By SUCCESS_MESSAGE = By.cssSelector("#successMessage.message");

    public SampleFormPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени = {firstName}")
    public SampleFormPage enterFirstName(String firstName) {
        sendKeysToElement(FIRST_NAME, firstName);

        return this;
    }

    @Step("Ввод фамилии = {lastName}")
    public SampleFormPage enterLastName(String lastName) {
        sendKeysToElement(LAST_NAME, lastName);

        return this;
    }

    @Step("Ввод почты = {email}")
    public SampleFormPage enterEmail(String email) {
        sendKeysToElement(EMAIL, email);

        return this;
    }

    @Step("Ввод пароля = {password}")
    public SampleFormPage enterPassword(String password) {
        sendKeysToElement(PASSWORD, password);

        return this;
    }

    @Step("Выбор хобби = {hobby}")
    public SampleFormPage selectHobby(String hobby) {
        By hobbyLocator = By.cssSelector("input[name='hobbies'][value='" + hobby + "']");
        clickElement(hobbyLocator);

        return this;
    }

    @Step("Получение списка хобби")
    public List<String> getHobbiesList() {
        List<WebElement> labels = findVisibleElements(HOBBIES_LABELS);

        return labels.stream()
                .map(label -> label.getText().strip())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    @Step("Выбор гендера = {gender}")
    public SampleFormPage selectGender(String gender) {
        WebElement dropdown = findVisibleElement(GENDER_DROPDOWN);
        dropdown.findElement(By.xpath(".//option[@value='" + gender.toLowerCase() + "']")).click();

        return this;
    }

    @Step("Ввод информации о себе = {aboutYourself}")
    public SampleFormPage enterAboutYourself(String aboutYourself) {
        sendKeysToElement(ABOUT_TEXTAREA, aboutYourself);

        return this;
    }

    @Step("Нажатие на кнопку 'Register'")
    public SampleFormPage register() {
        clickElement(REGISTER_BUTTON);

        return this;
    }

    @Step("Проверка отображения сообщения об успешной регистрации")
    public boolean isSuccessMessageDisplayed() {
        return findVisibleElement(SUCCESS_MESSAGE).isDisplayed();
    }
}
