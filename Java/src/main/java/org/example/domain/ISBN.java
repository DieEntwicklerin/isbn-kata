package org.example.domain;

import org.example.presentation.BookSearchViewModel;
import org.example.utils.StringUtils;

public class ISBN {
    private String isbn;

    public ISBN(String isbn) {
        this.isbn = isbn;
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
        String isbnWithoutSeparators = StringUtils.removeSeparators(this.isbn);
        if (isbnWithoutSeparators.length() == 10) {
            return StringUtils.replaceAllHyphens(this.isbn, " ");
        } else if (isbnWithoutSeparators.length() == 13) {
            return StringUtils.replaceAllSpaces(this.isbn, "-");
        }
        return null;
    }

    public String convertToEan() {
       return BookSearchViewModel.convertToEan(this.isbn);
    }
}
