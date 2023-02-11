package org.example.domain;

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
        return StringUtils.isWellFormedIsbn(this.isbn);
    }
}
