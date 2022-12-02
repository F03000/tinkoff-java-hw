package ru.tinkoff.academy.tinkofflibrary.book;

import org.springframework.stereotype.Repository;
import ru.tinkoff.academy.tinkofflibrary.AbstractRepository;

import java.util.List;

@Repository
public class BookRepository extends AbstractRepository<Book> {

    /**
     * ДЗ:
     * - Сделать List, который будет хранить набор сущностей Book.
     * Поиск книги, добавление и удаление из коллекци должно происходить
     * через соответствующие методы репозитория
     * <p>
     * - Сделать абстрактный обобщенный класс, который будет работать с любым типом сущности
     * и реализовывать стандартные методы: save, findById, remove, findAll.
     * Объявления репозитория для книг в таком случае будет выглядить как
     * class BookRepository extends AbstractRepository<Book>
     */

    public List<Book> findByAuthor(String authorName) {
        return null;
    }

}
