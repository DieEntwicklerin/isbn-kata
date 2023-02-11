package org.example;

import org.example.domain.Book;
import org.example.persistence.FakeInMemoryBookRepository;

import java.util.List;
import java.util.Optional;

public class BookRepositoryMock extends FakeInMemoryBookRepository {

    private final List<Book> allBooks = List.of(
            new Book("978-3-16-148410-0", "Example Book", "Jane Doe"),
            new Book("386680192-0", "Example Book Two", "Mister X")
    );

    @Override
    public Optional<Book> byIsbn(String isbn) {
        return allBooks
                .stream()
                .filter(book -> isEquivalentIsbn(book.getIsbnInStandardFormat(), isbn))
                .findFirst();
    }
}
