package com.echo.books.controllers;

import static com.echo.books.TestData.testBook;

import javax.swing.tree.ExpandVetoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import com.echo.books.TestData;
import com.echo.books.domain.Book;
import com.echo.books.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BookControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private BookService bookService;

  @Test
  public void testThatBookIsCreated() throws Exception{
    final Book book = TestData.testBook();
    final ObjectMapper objectMapper = new ObjectMapper();
    final String bookJson = objectMapper.writeValueAsString(book);

    mockMvc.perform(MockMvcRequestBuilders.put("/books/"+ book.getIsbn()).contentType("application/json").content(bookJson))
    .andExpect(MockMvcResultMatchers.content().json(bookJson));
  }

  @Test
  public void testThatRetrieveBookReturn404WhenBookNotFound() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/books/123"))
    .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testThatRetrieveBookReturnBookWhenExist() throws Exception{
    final Book book = TestData.testBook();
    bookService.save(book);

    mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getIsbn()))
    .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
