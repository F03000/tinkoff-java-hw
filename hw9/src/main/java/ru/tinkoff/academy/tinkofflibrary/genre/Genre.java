package ru.tinkoff.academy.tinkofflibrary.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.tinkoff.academy.tinkofflibrary.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends Entity {
    private String name;

    @Builder
    public Genre(String name, Long id) {
        super(id);
        this.name = name;
    }
}
