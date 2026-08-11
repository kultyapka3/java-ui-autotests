package com.company.tests.alert;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.data.InputAlertData;
import com.company.pages.AlertPage;

/** ТК16. Проверка работы Alert */
@Epic("Way2Automation")
@Feature("Alert")
@Story("Проверка работы Alert")
@Severity(SeverityLevel.CRITICAL)
public class InputAlertTest extends BaseTest {

    @Test(
            description = "ТК16. Проверка работы Alert",
            groups = {"way2automation", "alert"})
    public void testInputAlert() {
        AlertPage alertPage = createAlertPage();

        Allure.step(
                "Переход на INPUT ALERT и его заполнение",
                () -> {
                    alertPage
                            .clickSwitchToInputAlert()
                            .switchToIFrame()
                            .clickDemonstrateAlert()
                            .enterAlert(InputAlertData.ALERT_TEXT)
                            .acceptInputAlert();
                });

        Allure.step(
                "Проверка текста, введенного в алерт",
                () -> {
                    String resultText = alertPage.getAlertResultText();

                    Assert.assertTrue(
                            resultText.contains(InputAlertData.ALERT_TEXT),
                            String.format(
                                    "Текст (%s) не содержит ожидаемого значения (%s)",
                                    resultText, InputAlertData.ALERT_TEXT));
                });

        alertPage.switchFromIFrame();
    }
}
