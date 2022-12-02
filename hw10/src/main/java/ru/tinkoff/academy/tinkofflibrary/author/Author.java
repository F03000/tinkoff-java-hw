package ru.tinkoff.academy.tinkofflibrary.author;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="author")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String nameT;

}
