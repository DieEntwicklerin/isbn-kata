package org.example.persistence;

import org.example.domain.Book;
import org.example.domain.BookRepository;
import org.example.utils.StringUtils;
import org.example.domain.ISBN;

import java.util.List;
import java.util.Optional;

public class FakeInMemoryBookRepository implements BookRepository {

    private final List<Book> allBooks = List.of(
            new Book(new ISBN("978-3-16-148410-0"), "Example Book", "Jane Doe")
    );

    @Override
    public Optional<Book> byIsbn(String isbn) {
        return allBooks
                .stream()
                .filter(book -> isEquivalentIsbn(book.getIsbn().toString(), isbn))
                .findFirst();
    }

    protected boolean isEquivalentIsbn(String isbn1, String isbn2) {
        //TODO check if this is still needed
        return StringUtils.removeSeparators(isbn1).equals(StringUtils.removeSeparators(isbn2));
    }

    @Override
    public Optional<Book> byIsbn(ISBN isbn) {
        if (isbn == null)
            return null;
        return allBooks
                .stream()
                .filter(book -> isEquivalentIsbn(book.getIsbn().displayInCorrectFormatBasedOnLength(), isbn.displayInCorrectFormatBasedOnLength()))
                .findFirst();
    }
}
