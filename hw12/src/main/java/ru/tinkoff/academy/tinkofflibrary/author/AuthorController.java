package ru.tinkoff.academy.tinkofflibrary.author;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.tinkofflibrary.author.dto.CreatingAuthorDto;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{authorId}")
    public Author getById(@PathVariable Long authorId) {
        return authorService.getById(authorId);
    }

    @PostMapping
    public Author save(@RequestBody CreatingAuthorDto authorDto) {
        return authorService.save(authorDto);
    }

}
