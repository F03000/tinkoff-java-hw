package ru.tinkoff.academy.tinkofflibrary.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.tinkofflibrary.core.exception.EntityNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author getById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author didn't found by id=" + authorId));
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

}
