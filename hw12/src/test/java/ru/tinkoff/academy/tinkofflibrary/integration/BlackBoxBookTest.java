package ru.tinkoff.academy.tinkofflibrary.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.tinkoff.academy.tinkofflibrary.Containers;
import ru.tinkoff.academy.tinkofflibrary.author.Author;
import ru.tinkoff.academy.tinkofflibrary.book.Book;
import ru.tinkoff.academy.tinkofflibrary.genre.Genre;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import static java.net.http.HttpResponse.BodyHandlers;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(Containers.class)
public class BlackBoxBookTest {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .build();

    @Test
    void saveNewUserAndFindAll() throws IOException, InterruptedException {
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

        // when
        final HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(Containers.appContainer.getURI().resolve(URI.create("/books/1")))
                .build();

        final HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        Book responseBook = new ObjectMapper().readValue(response.body(), Book.class);

        // then
        assertEquals(200, response.statusCode(), response.body());
        assertEquals(expectedBook, responseBook);
    }


}
