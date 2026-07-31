package com.company.base;

import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.ITestResult;

import com.company.config.Config;
import com.company.pages.BankingAppPage;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;
import com.company.pages.SqlExPage;

/** Базовый класс для всех тестов */
@Listeners(AllureTestNg.class)
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
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

    /** Создание страницы SQL-Ex */
    protected SqlExPage createSqlExPage() {
        SqlExPage page = new SqlExPage(this.driver);
        page.open(Config.getSQLExUrl());

        return page;
    }

    /** Добавление скриншота в Allure отчет */
    @Attachment(value = "Screenshot_{testName}", type = "image/png")
    public byte[] takeScreenshot(String testName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                takeScreenshot(result.getMethod().getMethodName());
            } catch (Exception e) {
                System.err.println("Ошибка при создании скриншота: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
