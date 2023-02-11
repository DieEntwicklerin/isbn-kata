package org.example.utils;

public class StringUtils {

    public static String removeSeparators(String string) {
        return string.replace(" ", "").replace("-", "");
    }

    public static String displayInCorrectFormatBasedOnLength(String isbn) {
        if (removeSeparators(isbn).length() == 10) {
            return replaceAllHyphens(isbn, " ");
        } else if (removeSeparators(isbn).length() == 13) {
            return replaceAllSpaces(isbn, "-");
        }
        return null;
    }

    public static String replaceAllHyphens(String string, String replacement) {
        return string.replace("-", replacement);
    }

    public static String replaceAllSpaces(String string, String replacement) {
        return string.replace(" ", replacement);
    }
}
