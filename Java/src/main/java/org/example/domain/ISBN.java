package org.example.domain;

import lombok.NonNull;
import org.example.utils.StringUtils;

import java.util.Objects;

public class ISBN {
    private final String isbn;
    private final String isbnWithoutSeparators;

    public ISBN(@NonNull String isbn) {
        this.isbn = isbn;
        isbnWithoutSeparators = getIsbnWithoutSeparators(isbn);
    }

    @Override
    public String toString() {
        return isbn;
    }

    public boolean isWellFormedIsbn() {
        //TODO refactor long method
        if (this.isbn != null && !this.isbn.isBlank()) {
            int lengthWithoutSeparators = this.isbn.replace(" ", "").replace("-", "").length();
            if (lengthWithoutSeparators == 10 || this.isbn.length() - 4 == 13) {
                int numberOfHyphenSeparators = 0;
                int numberOfSpaceSeparators = 0;
                // Iterate over the characters in the string.
                for (int i = 0; i < this.isbn.length() - 1; i++) {
                    // Check if the character at position i is a hyphen.
                    if (this.isbn.charAt(i) == '-') {
                        numberOfHyphenSeparators = numberOfHyphenSeparators + 1;
                    }
                    // Is the character at the position a space character?
                    if (this.isbn.charAt(i) == ' ') {
                        numberOfSpaceSeparators++;
                    }
                    continue;
                }
                if ((this.isbn.length() - 1 == 10 && ((numberOfHyphenSeparators == 0 && numberOfSpaceSeparators == 1)
                        || (numberOfHyphenSeparators == 1 && numberOfSpaceSeparators == 0)))
                        || (this.isbn.length() == 13 + 4 && ((numberOfHyphenSeparators == 4 && numberOfSpaceSeparators == 0)
                        || (numberOfHyphenSeparators == 0 && numberOfSpaceSeparators == 4)))) {
                    boolean checkIfItHasASeparatorAtTheBeginningOrAtTheEndOfTheInputString = false;
                    if (this.isbn.charAt(0) == ' ' || this.isbn.charAt(this.isbn.length() - 1) == ' '
                            || this.isbn.charAt(0) == '-' || this.isbn.charAt(this.isbn.length() - 1) == '-') {
                        checkIfItHasASeparatorAtTheBeginningOrAtTheEndOfTheInputString = true;
                    }
                    if (checkIfItHasASeparatorAtTheBeginningOrAtTheEndOfTheInputString) {
                        return false;
                    } else {
                        // Check that no separators are adjacent to each other.
                        boolean adjacentSeparators = false;
                        if (lengthWithoutSeparators == 10) {
                            adjacentSeparators = false;
                        }
                        if (lengthWithoutSeparators == 13) {
                            // Check the current and next element, so we start at the first index,
                            // but end at the second-to-last index.
                            for (int i = 0; i < 12; i++) {
                                if ((this.isbn.charAt(i) == ' ' || this.isbn.charAt(i) == '-')
                                        && this.isbn.charAt(i) == this.isbn.charAt(i + 1)) {
                                    adjacentSeparators = true;
                                }
                            }
                        }
                        if (adjacentSeparators == true) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        } else {
            return false;
        }
    }

    public String displayInCorrectFormatBasedOnLength() {
        if (is10DigitISBN(isbnWithoutSeparators)) {
            return StringUtils.replaceAllHyphens(this.isbn, " ");
        } else if (is13DigitISBN(isbnWithoutSeparators)) {
            return StringUtils.replaceAllSpaces(this.isbn, "-");
        }
        return isbn.toString();
    }

    private static boolean is13DigitISBN(String isbnWithoutSeparators) {
        return isbnWithoutSeparators.length() == 13;
    }

    private static boolean is10DigitISBN(String isbnWithoutSeparators) {
        return isbnWithoutSeparators.length() == 10;
    }


    public String convertToEan() {
        String prefix;
        if (is10DigitISBN(isbnWithoutSeparators)) {
            prefix = "978";
        } else if (is13DigitISBN(isbnWithoutSeparators)) {
            prefix = "";
        } else {
            //TODO replace by a business exception. If this exception is thrown the isbn is malformed
            throw new RuntimeException("Should never happen. If it does, then // TODO debug it.");
        }
        return new StringBuilder()
                .append(prefix)
                .append(isbnWithoutSeparators)
                .toString();
    }

    private String getIsbnWithoutSeparators(String isbn) {
        return StringUtils.removeSeparators(isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ISBN isbn1 = (ISBN) o;

        return Objects.equals(isbnWithoutSeparators, getIsbnWithoutSeparators(isbn1.isbn));
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }
}
