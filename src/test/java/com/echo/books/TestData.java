package com.echo.books;

import com.echo.books.domain.Book;
import com.echo.books.domain.BookEntity;

public final class TestData {
  private TestData(){

  }

  public static Book testBook(){
    return Book.builder()
    .isbn("0123456")
    .author("echooo yang")
    .title("the developer")
    .build();
  }

  public static BookEntity testBookEntity(){
    return BookEntity.builder()
    .isbn("0123456")
    .author("echooo yang")
    .title("the developer")
    .build();
  }
  
}
