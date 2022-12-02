package ru.tinkoff.academy.tinkofflibrary.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    /**
     * ДЗ:
     * - Реализовать метод для удаления сущности (use HTTP method DELETE)
     * <p>
     * - *Вместо метода getAll сделать метод search, который на основе параметров запроса будет
     * фильтровать сущности по ее параметрам. Если никакие параметры не указаны, то будут возвращаться
     * все сущности из репозитория. Ожидаемый url:
     * <p>
     * localhost:8080/books/search?author=pushkin&genre=non-fiction
     * ** Используйте @RequestParam **
     */

    private final BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getById(@PathVariable Long bookId) {
        Optional<Book> book = bookService.findById(bookId);

        if (book.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody CreatingBookDto creatingBookDto) {
        try {
            Book savedBook = bookService.save(creatingBookDto);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Long> deleteById(@PathVariable Long bookId) {
        bookService.remove(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
