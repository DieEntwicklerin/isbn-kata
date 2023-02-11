package org.example.presentation;

import org.example.domain.Book;
import org.example.domain.BookRepository;
import org.example.domain.ISBN;

import java.util.Optional;
import java.util.function.Consumer;

public class BookSearchViewModel {

    private final BookRepository bookRepository;
    private final Consumer<String> searchResultHandler;
    private final Consumer<String> illFormedIsbnErrorHandler;
    private final Consumer<String> bookNotFoundErrorHandler;

    public BookSearchViewModel(
            BookRepository bookRepository,
            Consumer<String> searchResultHandler,
            Consumer<String> illFormedIsbnErrorHandler,
            Consumer<String> bookNotFoundErrorHandler) {
        this.bookRepository = bookRepository;
        this.searchResultHandler = searchResultHandler;
        this.illFormedIsbnErrorHandler = illFormedIsbnErrorHandler;
        this.bookNotFoundErrorHandler = bookNotFoundErrorHandler;
    }

    public void searchBook(String isbn) {
        ISBN isbnEntity= new ISBN(isbn);
        if (!isbnEntity.isWellFormedIsbn()) {
            onIllFormedIsbn(isbn);
            return;
        }

        final Optional<Book> exampleBook = bookRepository.byIsbn(isbnEntity);
        exampleBook.ifPresentOrElse(
                this::onBookFound,
                () -> onBookNotFound(isbnEntity)
        );
    }

    private void onBookFound(Book book) {
        searchResultHandler.accept(book.getStringRepresentationOfBook());
    }

    private void onIllFormedIsbn(String illFormedIsbn) {
        illFormedIsbnErrorHandler.accept(illFormedIsbn);
    }

    private void onBookNotFound(ISBN isbn) {
        bookNotFoundErrorHandler.accept(isbn.displayInCorrectFormatBasedOnLength());
    }
}
