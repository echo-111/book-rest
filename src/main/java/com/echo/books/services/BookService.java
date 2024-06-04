package com.echo.books.services;
import java.util.List;
import java.util.Optional;

import com.echo.books.domain.Book;

public interface BookService {
  
  boolean isBookExists(Book book);

  Optional<Book> findById(String isbn);

  List<Book> listBooks();

  Book save(Book book);

  void deleteBookById(String isbn);
}
