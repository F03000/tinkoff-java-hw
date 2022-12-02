package ru.tinkoff.academy.tinkofflibrary.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author save(CreatingAuthorDto authorDto) {
        long id = authorRepository.getFreeId();
        Author author = Author.builder().name(authorDto.getName()).id(id).build();
        return authorRepository.save(author);
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void remove(Long bookId) {
        authorRepository.remove(bookId);
    }
}
