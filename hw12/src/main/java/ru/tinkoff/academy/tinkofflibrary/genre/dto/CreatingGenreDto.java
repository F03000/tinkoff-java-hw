package ru.tinkoff.academy.tinkofflibrary.genre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatingGenreDto {
    private String name;
}
