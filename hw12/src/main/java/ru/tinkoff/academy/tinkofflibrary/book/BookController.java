package ru.tinkoff.academy.tinkofflibrary.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.tinkofflibrary.book.dto.CreatingBookDto;
import ru.tinkoff.academy.tinkofflibrary.book.request.GettingRequest;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public Page<Book> search(@RequestParam Map<String, String> requestParameters) {
        int page = Integer.parseInt(requestParameters.getOrDefault("page", "0"));
        requestParameters.remove("page");

        return bookService.search(page, requestParameters);
    }

    @GetMapping
    public Page<Book> getByPage(GettingRequest gettingRequest) {
        return bookService.findByPage(gettingRequest);
    }

    @GetMapping("/{bookId}")
    public Book getById(@PathVariable Long bookId) {
        return bookService.getById(bookId);
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
