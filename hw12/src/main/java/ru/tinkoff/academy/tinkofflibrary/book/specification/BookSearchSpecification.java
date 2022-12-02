package ru.tinkoff.academy.tinkofflibrary.book.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.tinkoff.academy.tinkofflibrary.book.Book;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

public class BookSearchSpecification {

    public static Specification<Book> searchByParams(Map<String, String> fieldWithValues) {
        return (book, cq, cb) -> {
            List<Predicate> predicates = fieldWithValues.entrySet().stream()
                    .map(entry -> cb.equal(book.get(entry.getKey()), entry.getValue()))
                    .toList();

            // id = 1
            // title = ""
            // id = 1 and title=""
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
