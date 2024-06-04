package com.echo.books.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.echo.books.TestData.testBook;
import static com.echo.books.TestData.testBookEntity;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.echo.books.domain.Book;
import com.echo.books.domain.BookEntity;
import com.echo.books.repositories.BookRepository;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
  @Mock
  private BookRepository bookRepository;
  
  @InjectMocks
  private BookServiceImpl underTest;

  @Test
  public void testThatBookIsSaved() {
    final Book book = testBook();
        

        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);

        final Book resule = underTest.save(book);
        assertEquals(book, resule);

  }

  @Test
  public void testThatFindByIdReturnEmptyWhenNoBook(){
    final String isbn = "123";
    when(bookRepository.findById(eq(isbn))).thenReturn(Optional.empty());

    final Optional<Book> result = underTest.findById(isbn);
    assertEquals(Optional.empty(), result);
  }


  @Test
  public void testThatFindByIdReturnsBookWhenExists() {
    final Book book = testBook();
    final BookEntity bookEntity = testBookEntity();

    when(bookRepository.findById(eq(book.getIsbn()))).thenReturn(Optional.of(bookEntity));

    final Optional<Book> result = underTest.findById(book.getIsbn());
    assertEquals(Optional.of(book), result);
  }

  @Test
  public void testListBooksReturnEmptyListWhenNoBookExist() {
    when(bookRepository.findAll()).thenReturn(new ArrayList<BookEntity>());

    final List<Book> result = underTest.listBooks();
    assertEquals(0, result.size());
  }

  @Test
  public void testListBooksReturnBookExist() {
    final BookEntity bookEntity = testBookEntity();
    when(bookRepository.findAll()).thenReturn(List.of(bookEntity));

    final List<Book> result = underTest.listBooks();
    assertEquals(1, result.size());
  }
}
