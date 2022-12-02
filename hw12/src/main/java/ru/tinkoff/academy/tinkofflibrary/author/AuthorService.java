package ru.tinkoff.academy.tinkofflibrary.author;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.tinkofflibrary.author.dto.CreatingAuthorDto;
import ru.tinkoff.academy.tinkofflibrary.core.exception.EntityNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final ModelMapper modelMapper;

    private final AuthorRepository authorRepository;

    public Author getById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author didn't found by id=" + authorId));
    }

    public List<Author> findAllByIds(List<Long> authorIds) {
        return authorRepository.findAllById(authorIds);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author save(CreatingAuthorDto authorDto) {
        // MapStruct
        Author author = modelMapper.map(authorDto, Author.class);

        return authorRepository.save(author);
    }
}
