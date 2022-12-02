package ru.tinkoff.academy.tinkofflibrary.author;

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
public class Author extends Entity {
    private String name;

    @Builder
    public Author(String name, Long id) {
        super(id);
        this.name = name;
    }
}
