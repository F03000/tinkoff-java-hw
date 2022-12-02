package ru.tinkoff.academy.tinkofflibrary;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AbstractRepository<T extends Entity> {
    private final List<T> entityList = new ArrayList<>();
    private long freeId = 1;

    public <S extends T> S save(S entity) {
        entityList.add(entity);
        return entity;
    }

    public void remove(Long id) {
        List<T> toRemove = entityList.stream().filter(x -> Objects.equals(x.getId(), id)).toList();
        toRemove.forEach(entityList::remove);
    }

    public Optional<T> findById(Long id) {
        return entityList.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst();
    }

    public List<T> findAll() {
        return entityList;
    }

    public long getFreeId() {
        return freeId++;
    }
}
