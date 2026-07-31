package com.company.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

/** Утильный класс для работы с Cookies */
public class CookieUtils {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static class CookieData {
        String name, value, domain, path;
    }

    /** Сохранение Cookies в файл */
    public static void saveCookies(WebDriver driver, String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());

            List<CookieData> cookieDataList =
                    driver.manage().getCookies().stream()
                            .map(
                                    cookie -> {
                                        CookieData data = new CookieData();
                                        data.name = cookie.getName();
                                        data.value = cookie.getValue();
                                        data.domain = cookie.getDomain();
                                        data.path = cookie.getPath();
                                        return data;
                                    })
                            .toList();

            try (FileWriter writer = new FileWriter(path.toFile())) {
                gson.toJson(cookieDataList, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении Cookies: ", e);
        }
    }

    /** Загрузка Cookies из файла */
    public static void loadCookies(WebDriver driver, String filePath) {
        driver.manage().deleteAllCookies();

        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new RuntimeException(
                    "Файл с Cookies не найден по пути: " + path.toAbsolutePath());
        }

        try (FileReader reader = new FileReader(path.toFile())) {
            Type listType = new TypeToken<List<CookieData>>() {}.getType();
            List<CookieData> cookieDataList = gson.fromJson(reader, listType);

            for (CookieData data : cookieDataList) {
                Cookie cookie =
                        new Cookie.Builder(data.name, data.value)
                                .domain(data.domain)
                                .path(data.path)
                                .build();
                driver.manage().addCookie(cookie);
            }

            driver.navigate().refresh();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке Cookies: ", e);
        }
    }
}
