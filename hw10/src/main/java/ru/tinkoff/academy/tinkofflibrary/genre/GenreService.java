package ru.tinkoff.academy.tinkofflibrary.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.tinkofflibrary.core.exception.EntityNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Optional<Genre> findById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    public Genre getById(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre didn't find by id=" + genreId));
    }

}
