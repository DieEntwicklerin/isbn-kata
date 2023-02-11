package org.example.domain;

public class ISBN {
    private String isbn;

    public ISBN(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return isbn;
    }
}
