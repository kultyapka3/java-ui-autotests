package com.company.base;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.company.config.Config;
import com.company.pages.BankingAppPage;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;

/** Базовый класс для всех тестов */
@Listeners(AllureTestNg.class)
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    /** Создание главной страницы */
    protected HomePage createHomePage() {
        HomePage page = new HomePage(this.driver);
        page.open(Config.getHomePageUrl());

        return page;
    }

    /** Создание страницы авторизации */
    protected LoginPage createLoginPage() {
        LoginPage page = new LoginPage(this.driver);
        page.open(Config.getLoginUrl());

        return page;
    }

    /** Создание страницы Banking App */
    protected BankingAppPage createBankingAppPage() {
        BankingAppPage page = new BankingAppPage(this.driver);
        page.open(Config.getBankingAppUrl());

        return page;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            ITestResult result = Reporter.getCurrentTestResult();

            if (result != null && result.getStatus() == ITestResult.FAILURE) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                Allure.getLifecycle()
                        .addAttachment(
                                "Screenshot_" + result.getMethod().getMethodName(),
                                "image/png",
                                "png",
                                screenshot);
            }

            driver.quit();
        }
    }
}
