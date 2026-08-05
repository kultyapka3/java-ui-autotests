package com.company.base;

import java.net.URL;

import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
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
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browserName) {
        String gridUrl = System.getProperty("grid.url");

        try {
            if (gridUrl != null && !gridUrl.isEmpty()) {
                System.out.println(
                        "Запуск на Selenium Grid: " + gridUrl + "\n  Браузер: " + browserName);
                driver = createRemoteDriver(browserName, gridUrl);
            } else {
                System.out.println("Локальный запуск\n  Браузер: " + browserName);
                driver = createLocalDriver(browserName);
            }

            driver.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при инициализации WebDriver: " + e.getMessage(), e);
        }
    }

    /** Создание драйвера для Selenium Grid */
    private WebDriver createRemoteDriver(String browserName, String gridUrl) throws Exception {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return new RemoteWebDriver(new URL(gridUrl), getChromeOptions(true));
            case "firefox":
                return new RemoteWebDriver(new URL(gridUrl), getFirefoxOptions(true));
            case "microsoftedge", "edge":
                return new RemoteWebDriver(new URL(gridUrl), getEdgeOptions(true));
            default:
                throw new IllegalArgumentException(
                        "Неподдерживаемый браузер для Grid: " + browserName);
        }
    }

    /** Создание локального драйвера */
    private WebDriver createLocalDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return new ChromeDriver(getChromeOptions(false));
            case "firefox":
                return new FirefoxDriver(getFirefoxOptions(false));
            case "microsoftedge", "edge":
                return new EdgeDriver(getEdgeOptions(false));
            default:
                throw new IllegalArgumentException(
                        "Неподдерживаемый локальный браузер: " + browserName);
        }
    }

    /** Настройки для Chrome */
    private ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments(
                "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--disable-extensions");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    /** Настройки для Firefox */
    private FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        return options;
    }

    /** Настройки для Edge */
    private EdgeOptions getEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
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
