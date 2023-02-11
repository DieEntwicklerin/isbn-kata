package org.example.domain;

public class Book {

    private final ISBN isbn;
    private final String title;
    private final String author;

    public Book(String isbnString, String title, String author) {
        //TODO instanciate with isbn entity
        this.isbn = new ISBN(isbnString);
        this.title = title;
        this.author = author;
    }

    public String getIsbnInStandardFormat() {
        return isbn.displayInCorrectFormatBasedOnLength();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStringRepresentationOfBook() {
        var isbn1 = isbn.displayInCorrectFormatBasedOnLength();
        var ean = isbn.convertToEan();

        return String.format("%s, %s, %s, %s", isbn1, ean, getTitle(), getAuthor());
    }
}
