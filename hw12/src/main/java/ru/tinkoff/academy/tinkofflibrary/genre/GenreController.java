package ru.tinkoff.academy.tinkofflibrary.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.tinkofflibrary.genre.dto.CreatingGenreDto;

import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/{genreId}")
    public Genre getById(@PathVariable Long genreId) {
        return genreService.getById(genreId);
    }

    @PostMapping
    public Genre save(@RequestBody CreatingGenreDto genreDto) {
        return genreService.save(genreDto);
    }

}
