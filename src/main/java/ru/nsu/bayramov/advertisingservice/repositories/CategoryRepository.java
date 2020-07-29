package ru.nsu.bayramov.advertisingservice.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.bayramov.advertisingservice.model.domain.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByRequestName(String requestName);

    List<Category> findAllByDeletedFalse();
}
