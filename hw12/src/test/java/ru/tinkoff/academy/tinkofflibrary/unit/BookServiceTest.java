package ru.tinkoff.academy.tinkofflibrary.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.tinkoff.academy.tinkofflibrary.author.Author;
import ru.tinkoff.academy.tinkofflibrary.author.AuthorService;
import ru.tinkoff.academy.tinkofflibrary.book.Book;
import ru.tinkoff.academy.tinkofflibrary.book.BookRepository;
import ru.tinkoff.academy.tinkofflibrary.book.BookService;
import ru.tinkoff.academy.tinkofflibrary.book.dto.CreatingBookDto;
import ru.tinkoff.academy.tinkofflibrary.genre.Genre;
import ru.tinkoff.academy.tinkofflibrary.genre.GenreService;

import java.util.List;

public class BookServiceTest {

    private AuthorService authorService = Mockito.mock(AuthorService.class);
    private GenreService genreService = Mockito.mock(GenreService.class);

    private BookRepository bookRepository = Mockito.mock(BookRepository.class);

    private BookService bookService = new BookService(bookRepository, authorService, genreService);

    @Test
    public void testMapping()  {
        List<Author> authors = List.of(
                Author.builder().fullName("author").build()
        );

        Genre genre = Genre.builder().name("genre").build();

        Mockito.when(authorService.findAllByIds(List.of(1L)))
                .thenReturn(authors);

        Mockito.when(genreService.getById(2L))
                .thenReturn(genre);

        Book book = Book.builder()
                .title("sad")
                .authors(authors)
                .genre(genre)
                .build();

        Book expectedBook = Book.builder()
                .id(1L)
                .title("sad")
                .authors(authors)
                .genre(genre)
                .build();

        Mockito.when(bookRepository.save(book))
                .thenReturn(expectedBook);

        CreatingBookDto dto = CreatingBookDto.builder()
                .title("sad")
                .authorIds(List.of(1L))
                .genreId(2L)
                .build();

        Book actualBook = bookService.save(dto);

        Assertions.assertEquals(expectedBook, actualBook);
    }

}
