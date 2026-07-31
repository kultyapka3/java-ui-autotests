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
        return props.getProperty("sqlex.login");
    }

    /** Пароль SQL-Ex */
    public static String getSQLExPassword() {
        return props.getProperty("sqlex.password");
    }
}
