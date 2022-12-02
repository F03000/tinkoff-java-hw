package ru.tinkoff.academy.tinkofflibrary.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.tinkoff.academy.tinkofflibrary.Entity;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book extends Entity {
    private String name;
    private String authorName;
    private LocalDate publicationDate;
    private String genreName;

    @Builder
    public Book(String name, String authorName, LocalDate publicationDate, String genreName, Long id) {
        super(id);
        this.name = name;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
        this.genreName = genreName;
    }
}
