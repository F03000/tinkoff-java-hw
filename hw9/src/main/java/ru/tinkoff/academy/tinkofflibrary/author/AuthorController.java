package ru.tinkoff.academy.tinkofflibrary.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getById(@PathVariable Long authorId) {
        Optional<Author> author = authorService.findById(authorId);

        if (author.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(author.get(), HttpStatus.OK);
    }

    @PostMapping
    public Author save(@RequestBody CreatingAuthorDto creatingAuthorDto) {
        return authorService.save(creatingAuthorDto);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Long> deleteById(@PathVariable Long authorId) {
        authorService.remove(authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
