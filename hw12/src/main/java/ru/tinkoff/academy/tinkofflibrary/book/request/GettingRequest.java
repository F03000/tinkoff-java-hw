package ru.tinkoff.academy.tinkofflibrary.book.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GettingRequest {
    int page;
    int size;
    String sortField;
    String sortDirection;
}
