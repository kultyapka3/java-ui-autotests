package com.company.tests.u1;

import java.util.List;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.data.SampleFormRegistrationData;
import com.company.pages.BankingAppPage;
import com.company.pages.SampleFormPage;
import com.company.utils.StringUtils;

/** ТК07. Успешная регистрация в 'Sample Form' */
@Epic("Way2Automation")
@Feature("Banking App")
@Story("Успешная регистрация в 'Sample Form'")
@Severity(SeverityLevel.CRITICAL)
public class SampleFormRegistrationTest extends BaseTest {
    @Test(
            description = "TC07. Успешная регистрация в 'Sample Form'",
            groups = {"way2automation", "positive"})
    public void testSampleFormRegistration() {
        BankingAppPage bankingAppPage = createBankingAppPage();
        SampleFormPage sampleFormPage = bankingAppPage.goToSampleForm();

        Allure.step(
                "Заполнение формы и регистрация",
                () -> {
                    sampleFormPage
                            .enterFirstName(SampleFormRegistrationData.FIRST_NAME)
                            .enterLastName(SampleFormRegistrationData.LAST_NAME)
                            .enterEmail(SampleFormRegistrationData.EMAIL)
                            .enterPassword(SampleFormRegistrationData.PASSWORD)
                            .selectHobby(SampleFormRegistrationData.HOBBY_TO_SELECT)
                            .selectGender(SampleFormRegistrationData.GENDER);

                    List<String> hobbies = sampleFormPage.getHobbiesList();
                    String aboutYourself =
                            "Самое длинное слово из предложенных хобби — "
                                    + StringUtils.getLongestWord(hobbies);
                    sampleFormPage.enterAboutYourself(aboutYourself).register();
                });

        Allure.step(
                "Проверка появления сообщения об успешной регистрации",
                () -> {
                    Assert.assertTrue(
                            sampleFormPage.isSuccessMessageDisplayed(),
                            "Регистрация не прошла успешно");
                });
    }
}
