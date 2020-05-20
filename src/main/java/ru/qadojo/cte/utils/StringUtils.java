package ru.qadojo.cte.utils;

public class StringUtils {

    public static String nullOrString(Object value) {
        return value == null ? null : value.toString();
    }
}
