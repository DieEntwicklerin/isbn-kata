package org.example.presentation;

import org.example.DIContainer;
import org.example.domain.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class BookSearchViewModelTest {
    String result=null;
    String errorStream=null;
    private BookSearchViewModel bookSearchViewModel;

    @BeforeEach
    void setUp() {
        bookSearchViewModel = DIContainer.instantiateBookSearchViewModel(this::showSearchResult, this::showBookNotFoundErrorMessage);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void searchBookWithValidISBN() {
        String validISBN= "978-3-16-148410-0";
        bookSearchViewModel.searchBook(validISBN);
        Assertions.assertNotNull(result);
        Assertions.assertNull(errorStream);
    }

    @Test
    void searchBookWithInValidISBN() {
        String inValidISBN= "978-3-16-148410";
        bookSearchViewModel.searchBook(inValidISBN);
        Assertions.assertNull(result);
        Assertions.assertNotNull(errorStream);
    }

    private void showSearchResult(String result) {
        this.result= result;
    }

    private void showBookNotFoundErrorMessage(String isbn) {
        var errorMessage = String.format("No book found for the provided ISBN %s.", isbn);
        this.errorStream=errorMessage;
    }
}