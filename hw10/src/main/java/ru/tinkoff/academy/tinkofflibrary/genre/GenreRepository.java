package ru.tinkoff.academy.tinkofflibrary.genre;

import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.tinkofflibrary.core.repository.AbstractRepository;

import java.util.List;

@Repository
public class GenreRepository extends AbstractRepository<Genre> {

    public GenreRepository() {
        super(
            List.of(
                Genre.builder()
                        .id(1L)
                        .name("Realism")
                        .build()
            )
        );
    }

}
