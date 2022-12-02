package ru.tinkoff.academy.tinkofflibrary.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.tinkofflibrary.author.Author;
import ru.tinkoff.academy.tinkofflibrary.author.AuthorService;
import ru.tinkoff.academy.tinkofflibrary.core.exception.EntityNotFoundException;
import ru.tinkoff.academy.tinkofflibrary.genre.Genre;
import ru.tinkoff.academy.tinkofflibrary.genre.GenreService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(CreatingBookDto bookDto) {
        Book bookForSaving = Book.builder()
                .title(bookDto.getTitle())
                .build();

        return bookRepository.save(bookForSaving);
    }

    public void remove(Long bookId) {
         bookRepository.deleteById(bookId);
    }

    public Optional<Book> findById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book getByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle)
                .orElseThrow(() -> new EntityNotFoundException("Book didn't find by title=" + bookTitle));
    }
}
