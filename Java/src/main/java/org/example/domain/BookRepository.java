package org.example.domain;

import java.util.Optional;

public interface BookRepository {

    /**
     * @deprecated use {@link #byIsbn(ISBN)} instead. Will be removed with release 1.1
     */
    @Deprecated(since = "1.0", forRemoval = true)
    Optional<Book> byIsbn(String isbn);

    Optional<Book> byIsbn(ISBN validISBN);


}
