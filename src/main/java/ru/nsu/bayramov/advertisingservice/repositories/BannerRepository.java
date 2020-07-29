package ru.nsu.bayramov.advertisingservice.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.bayramov.advertisingservice.model.domain.Banner;
import ru.nsu.bayramov.advertisingservice.model.domain.Category;

import java.util.List;

public interface BannerRepository extends CrudRepository<Banner, Integer> {
    List<Banner> findAllByDeletedFalse();

    List<Banner> findAllByCategoryAndDeletedFalse(Category category);
}
