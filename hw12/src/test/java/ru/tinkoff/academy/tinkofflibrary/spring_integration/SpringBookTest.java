package ru.tinkoff.academy.tinkofflibrary.spring_integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.tinkoff.academy.tinkofflibrary.author.Author;
import ru.tinkoff.academy.tinkofflibrary.book.Book;
import ru.tinkoff.academy.tinkofflibrary.genre.Genre;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class SpringBookTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGettingBook() throws Exception {
        Book expectedBook = Book.builder()
                .id(1L)
                .price(new BigDecimal("25.65"))
                .title("book_test")
                .isVisible(true)
                .genre(
                    Genre.builder()
                        .id(1L)
                        .name("genre_test")
                        .build()
                ).authors(List.of(
                    Author.builder()
                        .id(1L)
                        .fullName("author_full_name")
                        .build()
                )).build();

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(status().isOk())
                .andReturn();

        Book responseBook = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Book.class);

        Assertions.assertEquals(expectedBook, responseBook);
    }

}
