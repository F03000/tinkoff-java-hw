package ru.tinkoff.academy.tinkofflibrary.genre;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.tinkofflibrary.core.exception.EntityNotFoundException;
import ru.tinkoff.academy.tinkofflibrary.genre.dto.CreatingGenreDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;

    public Genre getById(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre didn't find by id=" + genreId));
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre save(CreatingGenreDto genreDto) {
        Genre author = modelMapper.map(genreDto, Genre.class);

        return genreRepository.save(author);
    }
}
