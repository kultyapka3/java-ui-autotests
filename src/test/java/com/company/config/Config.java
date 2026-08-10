package com.company.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.Getter;

/** Конфигурация */
@Getter
public class Config {

    private static final Properties props = new Properties();

    static {
        try (InputStream is =
                Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Ошибка при загрузке конфига из файла config.properties: ", e);
        }
    }

    /** Стандартный тайм-аут */
    public static Integer getDefaultTimeout() {
        return 10;
    }

    /** Ссылка на главную страницу */
    public static String getHomePageUrl() {
        return "https://www.way2automation.com/";
    }

    /** Ссылка на Lifetime Membership страницу */
    public static String getLifetimeMembershipUrl() {
        return getHomePageUrl() + "lifetime-membership-club/";
    }

    /** Ссылка на страницу авторизации */
    public static String getLoginUrl() {
        return getHomePageUrl() + "angularjs-protractor/registeration/#/login/";
    }

    /** Ссылка на страницу Banking App */
    public static String getBankingAppUrl() {
        return getHomePageUrl() + "angularjs-protractor/banking/#/login/";
    }

    /** Адрес файла с Cookies */
    public static String getCookiesFilePath() {
        return "cookies/auth_cookies.json";
    }

    /** Ссылка на страницу SQL-Ex */
    public static String getSQLExUrl() {
        return "https://sql-ex.ru/";
    }

    /** Логин SQL-Ex */
    public static String getSQLExLogin() {
        String envLogin = System.getenv("SQLEX_LOGIN");
        if (envLogin != null && !envLogin.isEmpty()) {
            return envLogin;
        }

        return props.getProperty("sqlex.login");
    }

    /** Пароль SQL-Ex */
    public static String getSQLExPassword() {
        String envPassword = System.getenv("SQLEX_PASSWORD");
        if (envPassword != null && !envPassword.isEmpty()) {
            return envPassword;
        }

        return props.getProperty("sqlex.password");
    }

    /** Ссылка на страницу Drag and Drop */
    public static String getDragNDropUrl() {
        return getHomePageUrl() + "way2auto_jquery/droppable.php";
    }

    /** Ссылка на страницу Frames and Windows */
    public static String getFramesNWindowsUrl() {
        return getHomePageUrl() + "way2auto_jquery/frames-and-windows.php";
    }

    /** Ссылка на страницу Alert */
    public static String getAlertUrl() {
        return getHomePageUrl() + "way2auto_jquery/alert.php";
    }

    /** Ссылка на страницу Basic Auth */
    public static String getBasicAuthUrl() {
        return "https://www.httpwatch.com/httpgallery/authentication/";
    }
}
