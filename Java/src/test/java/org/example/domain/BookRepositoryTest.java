package org.example.domain;

import org.example.persistence.FakeInMemoryBookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    @Test
    void bookIsFoundForValidIsbn() {
        BookRepository bookRepository = new FakeInMemoryBookRepository();
        String validISBNString= "978-3-16-148410-0";
        ISBN validISBN= new  ISBN(validISBNString);
        Optional<Book> book = bookRepository.byIsbn(validISBN);
        Assertions.assertNotNull(book.get());
    }

    @Test
    void bookIsNotFoundForInValidIsbn() {
        BookRepository bookRepository = new FakeInMemoryBookRepository();
        String invalidISBNString= "978-3-16-";
        ISBN validISBN= new  ISBN(invalidISBNString);
        Optional<Book> book = bookRepository.byIsbn(validISBN);
        Assertions.assertTrue(book.isEmpty());
    }
}