package ru.tinkoff.academy.tinkofflibrary.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/{genreId}")
    public ResponseEntity<Genre> getById(@PathVariable Long genreId) {
        Optional<Genre> genre = genreService.findById(genreId);

        if (genre.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(genre.get(), HttpStatus.OK);
    }

}
