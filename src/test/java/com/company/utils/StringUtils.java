package com.company.utils;

import java.util.Comparator;
import java.util.List;

/** Утильный класс для работы со строками */
public class StringUtils {

    /** Поиск самого длинного слова в списке */
    public static String getLongestWord(List<String> words) {
        return words.stream().max(Comparator.comparingInt(String::length)).orElse("");
    }
}
