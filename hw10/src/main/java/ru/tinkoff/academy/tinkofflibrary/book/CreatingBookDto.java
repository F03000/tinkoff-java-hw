package ru.tinkoff.academy.tinkofflibrary.book;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatingBookDto {
    private String title;
    private Long authorId;
    private Long genreId;
    private LocalDate publicationDate;
}
