package ru.tinkoff.academy.tinkofflibrary.book.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatingBookDto {
    private String title;
    private boolean isVisible;
    private Long genreId;
//    private Long publisherId;
    private List<Long> authorIds;
    @Nullable
    private BigDecimal price;
}
