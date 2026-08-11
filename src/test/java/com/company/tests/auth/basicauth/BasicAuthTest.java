package com.company.tests.auth.basicauth;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.BasicAuthPage;

/** ТК17. Проверка работы Basic Auth */
@Epic("HttpWatch")
@Feature("Basic Auth")
@Story("Проверка работы Basic Auth")
@Severity(SeverityLevel.CRITICAL)
public class BasicAuthTest extends BaseTest {

    @Test(
            description = "ТК17. Проверка работы Basic Auth",
            groups = {"httpwatch", "basicauth"})
    public void testBasicAuth() {
        BasicAuthPage basicAuthPage = createBasicAuthPage();

        basicAuthPage.registerBasicAuth().clickDisplayImage();

        Allure.step(
                "Проверка успешной авторизации и загрузки изображения",
                () -> {
                    Assert.assertTrue(
                            basicAuthPage.isAuthSuccessful(),
                            "Авторизация не удалась, изображение не отображается");
                });
    }
}
