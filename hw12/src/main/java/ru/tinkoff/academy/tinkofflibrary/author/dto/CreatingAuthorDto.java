package ru.tinkoff.academy.tinkofflibrary.author.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatingAuthorDto {
    private String fullName;
}
