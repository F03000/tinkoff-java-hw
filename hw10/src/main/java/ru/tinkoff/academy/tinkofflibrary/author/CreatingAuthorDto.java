package ru.tinkoff.academy.tinkofflibrary.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatingAuthorDto {
    private String name;
}
