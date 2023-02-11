package org.example.domain;

public class Book {

    private final ISBN isbn;
    private final String title;
    private final String author;

    public Book(String isbnString, String title, String author) {
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
        //TODO change isbn usage
        ISBN isbnEntity = new ISBN(getIsbnInStandardFormat());

        var isbn1 = isbnEntity.displayInCorrectFormatBasedOnLength();
        var ean = isbnEntity.convertToEan();
        var title1 = getTitle();
        var author1 = getAuthor();

        var searchResult = String.format("%s, %s, %s, %s", isbn1, ean, title1, author1);
        return searchResult;
    }
}
