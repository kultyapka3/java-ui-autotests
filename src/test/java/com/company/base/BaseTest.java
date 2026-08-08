package com.company.base;

import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.ITestResult;

import com.company.config.Config;
import com.company.pages.BankingAppPage;
import com.company.pages.HomePage;
import com.company.pages.LoginPage;
import com.company.pages.SqlExPage;
import com.company.pages.DragNDropPage;
import com.company.utils.DriverFactory;

/** Базовый класс для всех тестов */
@Listeners(AllureTestNg.class)
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browserName) {
        String gridUrl = System.getProperty("grid.url");
        boolean isGrid = gridUrl != null && !gridUrl.isEmpty();

        try {
            if (isGrid) {
                System.out.println(
                        "Запуск на Selenium Grid: " + gridUrl + "\n  Браузер: " + browserName);
            } else {
                System.out.println("Локальный запуск\n  Браузер: " + browserName);
            }

            driver = DriverFactory.createDriver(browserName, isGrid, gridUrl);
            driver.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при инициализации WebDriver: " + e.getMessage(), e);
        }
    }

    // Фабрики для Page Object
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

    /** Создание страницы Drag and Drop */
    protected DragNDropPage createDragNDropPage() {
        DragNDropPage page = new DragNDropPage(this.driver);
        page.open(Config.getDragNDropUrl());

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
