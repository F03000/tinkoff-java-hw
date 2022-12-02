package ru.tinkoff.academy.tinkofflibrary.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.academy.tinkofflibrary.author.Author;
import ru.tinkoff.academy.tinkofflibrary.author.AuthorService;
import ru.tinkoff.academy.tinkofflibrary.book.dto.CreatingBookDto;
import ru.tinkoff.academy.tinkofflibrary.book.request.GettingRequest;
import ru.tinkoff.academy.tinkofflibrary.book.specification.BookSearchSpecification;
import ru.tinkoff.academy.tinkofflibrary.core.exception.EntityNotFoundException;
import ru.tinkoff.academy.tinkofflibrary.genre.Genre;
import ru.tinkoff.academy.tinkofflibrary.genre.GenreService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;
    private final GenreService genreService;

    public Book getById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book didn't found by id=" + bookId));
    }

    public Page<Book> findByPage(GettingRequest gettingRequest) {
        Sort sort = Sort.by(
                Direction.valueOf(gettingRequest.getSortDirection()),
                gettingRequest.getSortField()
        );

        Pageable pageable = PageRequest.of(gettingRequest.getPage(), gettingRequest.getSize(), sort);

        return bookRepository.findAll(pageable);
    }

    public Page<Book> search(int page, Map<String, String> searchParameters) {
        Pageable pageable = PageRequest.of(page, 10);

        return bookRepository.findAll(
                BookSearchSpecification.searchByParams(searchParameters),
                pageable
        );
    }

    @Transactional
    public Book save(CreatingBookDto bookDto) {
        Genre genre = genreService.getById(bookDto.getGenreId());
        List<Author> authors = authorService.findAllByIds(bookDto.getAuthorIds());

        Book bookForSaving = Book.builder()
                .title(bookDto.getTitle())
                .price(bookDto.getPrice())
                .genre(genre)
                .authors(authors)
                .isVisible(bookDto.isVisible())
                .build();

        return bookRepository.save(bookForSaving);
    }

    public void remove(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
