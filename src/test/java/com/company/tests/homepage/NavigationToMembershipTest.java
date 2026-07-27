package com.company.tests.homepage;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.config.Config;
import com.company.pages.HomePage;
import com.company.pages.LifetimeMembershipPage;

/** ТК04. Переход по меню навигации на страницу Lifetime Membership */
@Epic("Way2Automation")
@Feature("Навигация")
@Story("Переход по меню навигации на страницу Lifetime Membership")
@Severity(SeverityLevel.NORMAL)
public class NavigationToMembershipTest extends BaseTest {

    @Test(
            description = "TC04. Переход по меню навигации на страницу Lifetime Membership",
            groups = {"way2automation", "positive"})
    public void testNavigationToMembership() {
        HomePage homePage = createHomePage();

        LifetimeMembershipPage lmPage = homePage.goToLifetimeMembership();

        Allure.step(
                "Проверка перехода на страницу Lifetime Membership",
                () -> {
                    Assert.assertEquals(
                            lmPage.getLifetimeMembershipUrl(),
                            Config.getLifetimeMembershipUrl(),
                            "Полученный URL не совпадает с ожидаемым");

                    String actualTitle = lmPage.getLifetimeMembershipTitle();
                    String expectedTitle = "Lifetime Membership";

                    Assert.assertTrue(
                            actualTitle.contains(expectedTitle),
                            String.format(
                                    "Ожидалось, что заголовок содержит (%s), но фактический заголовок: (%s)",
                                    expectedTitle, actualTitle));
                });
    }
}
