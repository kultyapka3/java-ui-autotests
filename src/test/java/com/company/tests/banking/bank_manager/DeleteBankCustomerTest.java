package com.company.tests.banking.bank_manager;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.data.BankManagerLoginData;
import com.company.pages.BankingAppPage;
import com.company.pages.BankManagerPage;

/** ТК08. Удаление покупателя в 'Bank Manager Login' */
@Epic("Way2Automation")
@Feature("Banking App")
@Story("Удаление покупателя в 'Bank Manager Login'")
@Severity(SeverityLevel.CRITICAL)
public class DeleteBankCustomerTest extends BaseTest {
    @Test(
            description = "TC08. Удаление покупателя в 'Bank Manager Login'",
            groups = {"way2automation", "positive"})
    public void testDeleteBankCustomer() {
        BankingAppPage bankingAppPage = createBankingAppPage();
        BankManagerPage bankManagerPage = bankingAppPage.goToBankManager();

        Allure.step(
                "Создание покупателя",
                () -> {
                    bankManagerPage
                            .goToAddCustomerTab()
                            .enterFirstName(BankManagerLoginData.FIRST_NAME)
                            .enterLastName(BankManagerLoginData.LAST_NAME)
                            .enterPostCode(BankManagerLoginData.POST_CODE)
                            .clickAddCustomer()
                            .acceptCustomerAlert();
                });

        bankManagerPage.goToCustomersTab().fillSearchCustomer(BankManagerLoginData.FIRST_NAME);

        Allure.step(
                "Удаление покупателя и проверка его отсутствия",
                () -> {
                    bankManagerPage
                            .deleteCustomer(BankManagerLoginData.FIRST_NAME)
                            .clearSearchCustomer();

                    Assert.assertFalse(
                            bankManagerPage.isCustomerPresent(BankManagerLoginData.FIRST_NAME),
                            "Покупатель с именем '"
                                    + BankManagerLoginData.FIRST_NAME
                                    + "' все еще присутствует в таблице после удаления");
                });
    }
}
