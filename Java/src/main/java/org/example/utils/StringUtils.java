package org.example.utils;

public class StringUtils {

    public static String removeSeparators(String string) {
        return string.replace(" ", "").replace("-", "");
    }

    public static String replaceAllHyphens(String string, String replacement) {
        return string.replace("-", replacement);
    }

    public static String replaceAllSpaces(String string, String replacement) {
        return string.replace(" ", replacement);
    }
}
