package ru.tinkoff.academy.tinkofflibrary.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.academy.tinkofflibrary.core.entity.RepositoryEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Genre implements RepositoryEntity<Genre> {

    private Long id;
    private String name;

    @Override
    public Genre getCopy() {
        return new Genre(id, name);
    }
}
