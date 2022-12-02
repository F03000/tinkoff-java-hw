package ru.tinkoff.academy.tinkofflibrary.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository authorRepository;

    public Genre save(CreatingGenreDto authorDto) {
        Genre author = Genre.builder().name(authorDto.getName()).id(authorRepository.getFreeId()).build();
        return authorRepository.save(author);
    }

    public void remove(Long id) {
        authorRepository.remove(id);
    }

    public Optional<Genre> findById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Genre> findAll() {
        return authorRepository.findAll();
    }

}
