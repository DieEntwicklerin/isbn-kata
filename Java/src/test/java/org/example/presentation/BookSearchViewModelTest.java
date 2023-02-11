package org.example.presentation;

import org.example.DIContainer;
import org.example.domain.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class BookSearchViewModelTest {
    String result=null;
    String errorStream=null;

    String invalidISBNStream=null;

    private BookSearchViewModel bookSearchViewModel;

    @BeforeEach
    public void setUp() {
        bookSearchViewModel = DIContainer.instantiateBookSearchViewModel(
                this::showSearchResult, this::showInvalidIsbnErrorMessage, this::showBookNotFoundErrorMessage);
    }

    @Test
    public  void searchBookWithValidISBN() {
        String validISBN= "978-3-16-148410-0";
        bookSearchViewModel.searchBook(validISBN);
        Assertions.assertNotNull(result);
        Assertions.assertNull(errorStream);
        Assertions.assertNull(invalidISBNStream);
    }

    @Test
    public  void searchBookWithInValidISBN() {
        String inValidISBN= "978-3-16-148410";
        bookSearchViewModel.searchBook(inValidISBN);
        Assertions.assertNull(result);
        Assertions.assertNotNull(invalidISBNStream);
        Assertions.assertNull(errorStream);
    }

    private void showSearchResult(String result) {
        this.result= result;
    }

    private void showBookNotFoundErrorMessage(String isbn) {
        this.errorStream= String.format("No book found for the provided ISBN %s.", isbn);
    }

    private void showInvalidIsbnErrorMessage(String illFormedIsbn) {
        invalidISBNStream= String.format("The provided search phrase `%s` is not a well-formed ISBN.", illFormedIsbn);
    }
}