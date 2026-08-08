package com.company.utils;

import java.net.URL;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/** Класс для создания экземпляров драйвера */
public class DriverFactory {

    /** Создание дройвера на основе переданных параметров */
    public static WebDriver createDriver(String browserName, boolean isGrid, String gridUrl) {
        if (isGrid) {
            return createRemoteDriver(browserName, gridUrl);
        } else {
            return createLocalDriver(browserName);
        }
    }

    /** Создание драйвера для Selenium Grid */
    private static WebDriver createRemoteDriver(String browserName, String gridUrl) {
        try {
            return switch (browserName.toLowerCase()) {
                case "chrome" -> new RemoteWebDriver(new URL(gridUrl), getChromeOptions(true));
                case "firefox" -> new RemoteWebDriver(new URL(gridUrl), getFirefoxOptions(true));
                case "microsoftedge", "edge" ->
                        new RemoteWebDriver(new URL(gridUrl), getEdgeOptions(true));
                case "internetexplorer", "ie" ->
                        new RemoteWebDriver(new URL(gridUrl), getIEOptions());
                default ->
                        throw new IllegalArgumentException(
                                "Неподдерживаемый браузер для Grid: " + browserName);
            };
        } catch (Exception e) {
            throw new RuntimeException("Не удалось подключиться к Selenium Grid: " + gridUrl, e);
        }
    }

    /** Создание локального драйвера */
    private static WebDriver createLocalDriver(String browserName) {
        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeDriver(getChromeOptions(false));
            case "firefox" -> new FirefoxDriver(getFirefoxOptions(false));
            case "microsoftedge", "edge" -> new EdgeDriver(getEdgeOptions(false));
            case "internetexplorer", "ie" -> new InternetExplorerDriver(getIEOptions());
            default ->
                    throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
        };
    }

    /** Настройки для Chrome */
    private static ChromeOptions getChromeOptions(boolean headless) {
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
    private static FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        return options;
    }

    /** Настройки для Edge */
    private static EdgeOptions getEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    /** Настройки для Internet Explorer */
    private static InternetExplorerOptions getIEOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.ignoreZoomSettings();
        options.introduceFlakinessByIgnoringSecurityDomains();
        options.requireWindowFocus();

        return options;
    }
}
