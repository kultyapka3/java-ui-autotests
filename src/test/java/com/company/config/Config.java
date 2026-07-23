package com.company.config;

import lombok.Getter;

/** Конфигурация */
@Getter
public class Config {

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
}
