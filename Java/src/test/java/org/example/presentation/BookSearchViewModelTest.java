package org.example.presentation;

import org.example.BookRepositoryMock;
import org.example.DIContainer;
import org.example.domain.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class BookSearchViewModelTest {
    String result = null;
    String errorStream = null;

    String invalidISBNStream = null;

    private BookSearchViewModel bookSearchViewModel;

    @BeforeEach
    public void setUp() {
        bookSearchViewModel =  new BookSearchViewModel( new BookRepositoryMock(), this::showSearchResult, this::showInvalidIsbnErrorMessage, this::showBookNotFoundErrorMessage);
    }

    @Test
    public void searchBookWithValid13DigitISBN() {
        String validISBN = "978-3-16-148410-0";
        bookSearchViewModel.searchBook(validISBN);
        Assertions.assertEquals("978-3-16-148410-0, 9783161484100, Example Book, Jane Doe", result);
        Assertions.assertNull(errorStream);
        Assertions.assertNull(invalidISBNStream);
    }

    @Test
    public void searchBookWithFormattedISBN() {
        String validISBN = "97-8-3-1-61484100";
        bookSearchViewModel.searchBook(validISBN);
        Assertions.assertEquals("978-3-16-148410-0, 9783161484100, Example Book, Jane Doe", result);
        Assertions.assertNull(errorStream);
        Assertions.assertNull(invalidISBNStream);
    }

    @Test
    public void searchBookWithValid10DigitISBN() {
        String validISBN = "386680192-0";
        bookSearchViewModel.searchBook(validISBN);
        Assertions.assertEquals("386680192 0, 9783866801920, Example Book Two, Mister X", result);
        Assertions.assertNull(errorStream);
        Assertions.assertNull(invalidISBNStream);
    }

    @Test
    public void searchBookWithValidButUnknownISBN() {
        String validISBN = "978-3-16-148435-0";
        bookSearchViewModel.searchBook(validISBN);
        Assertions.assertNull(result);
        Assertions.assertEquals(validISBN, errorStream);
        Assertions.assertNull(invalidISBNStream);
    }

    @Test
    public void searchBookWithInValidISBN() {
        String inValidISBN = "978-3-16-148410";
        bookSearchViewModel.searchBook(inValidISBN);
        Assertions.assertNull(result);
        Assertions.assertNull(errorStream);
        Assertions.assertEquals(inValidISBN, invalidISBNStream);
    }

    private void showSearchResult(String result) {
        this.result = result;
    }

    private void showBookNotFoundErrorMessage(String isbn) {
        this.errorStream = isbn;
    }

    private void showInvalidIsbnErrorMessage(String illFormedIsbn) {
        invalidISBNStream = illFormedIsbn;
    }
}