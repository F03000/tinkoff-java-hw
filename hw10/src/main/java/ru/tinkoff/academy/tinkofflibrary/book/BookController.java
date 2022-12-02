package ru.tinkoff.academy.tinkofflibrary.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/title")
    public Book getByTitle(@RequestParam String bookTitle) {
        return bookService.getByTitle(bookTitle);
    }

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
    public Book save(@RequestBody CreatingBookDto creatingBookDto) {
        return bookService.save(creatingBookDto);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable Long bookId) {
        bookService.remove(bookId);
    }
}
