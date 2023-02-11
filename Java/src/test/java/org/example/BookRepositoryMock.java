package org.example;

import org.example.domain.Book;
import org.example.domain.ISBN;
import org.example.persistence.FakeInMemoryBookRepository;

import java.util.List;
import java.util.Optional;

public class BookRepositoryMock extends FakeInMemoryBookRepository {

    private final List<Book> allBooks = List.of(
            new Book(new ISBN("978-3-16-148410-0"), "Example Book", "Jane Doe"),
            new Book(new ISBN("386680192-0"), "Example Book Two", "Mister X")
    );

    @Override
    public Optional<Book> byIsbn(ISBN isbn) {
        //TODO refactor: is a 100 percent copy of the super.byisbn. Needed because of the allbooks.
        if (isbn == null)
            return null;
        return allBooks
                .stream()
                .filter(book -> isEquivalentIsbn(book.getIsbn().displayInCorrectFormatBasedOnLength(), isbn.displayInCorrectFormatBasedOnLength()))
                .findFirst();
    }
}
