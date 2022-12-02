package ru.tinkoff.academy.tinkofflibrary.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.tinkofflibrary.author.Author;
import ru.tinkoff.academy.tinkofflibrary.author.AuthorService;
import ru.tinkoff.academy.tinkofflibrary.genre.Genre;
import ru.tinkoff.academy.tinkofflibrary.genre.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    /**
     * ДЗ:
     * - Реализовать контроллер, репозиторий и сервис для сущностей Genre и Author
     * - Иметь возможность найти автора и жанр по id. Имплементировать логику, которая приведена ниже в комментариях
     */

    private final AuthorService authorService;
    private final GenreService genreService;
    public static final String BAD_AUTHOR_MESSAGE = "No such author";
    public static final String BAD_GENRE_MESSAGE = "No such genre";

    private final BookRepository bookRepository;

    public Book save(CreatingBookDto bookDto) {
        Author author = authorService.findById(bookDto.getAuthorId()).orElseThrow(() -> new IllegalArgumentException(BAD_AUTHOR_MESSAGE));
        Genre genre = genreService.findById(bookDto.getGenreId()).orElseThrow(() -> new IllegalArgumentException(BAD_GENRE_MESSAGE));

        Book bookForSaving = Book.builder()
                .name(bookDto.getName())
                .authorName(author.getName())
                .genreName(genre.getName())
                .publicationDate(bookDto.getPublicationDate())
                .id(bookRepository.getFreeId())
                .build();

        return bookRepository.save(bookForSaving);
    }

    public Optional<Book> findById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void remove(Long bookId) {
        bookRepository.remove(bookId);
    }


}
