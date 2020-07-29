package ru.nsu.bayramov.advertisingservice.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.bayramov.advertisingservice.dtotransformservice.DtoTransformService;
import ru.nsu.bayramov.advertisingservice.model.domain.Banner;
import ru.nsu.bayramov.advertisingservice.model.domain.Category;
import ru.nsu.bayramov.advertisingservice.model.dto.CategoryDto;
import ru.nsu.bayramov.advertisingservice.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final DtoTransformService dtoTransformService;

    public void create(CategoryDto categoryDto) {
        categoryDto.setDeleted(false);
        categoryRepository.save(dtoTransformService.convertToCategory(categoryDto));
    }

    public void update(CategoryDto categoryDto) {
        categoryRepository.save(dtoTransformService.convertToCategory(categoryDto));
    }

    public List<Integer> delete(int categoryId) {
        Category category = categoryRepository.
                findById(categoryId).
                orElseThrow(() -> new IllegalArgumentException("Category with id " + categoryId + " not find"));

        List<Integer> banners = new ArrayList<>();

        boolean deleted = true;
        for (Banner banner : category.getBanners()) {
            if (!banner.getDeleted()) {
                deleted = false;
                banners.add(banner.getId());
            }
        }

        if (!deleted) {
            return banners;
        }

        category.setDeleted(true);
        categoryRepository.save(category);
        return null;
    }

    public List<CategoryDto> search(String searchString) {
        List<CategoryDto> categories = new ArrayList<>();

        for (Category category : categoryRepository.findAllByDeletedFalse()) {
            String bannerName = category.getName().toLowerCase();

            if (bannerName.contains(searchString.toLowerCase())) {
                categories.add(dtoTransformService.convertToCategoryDto(category));
            }
        }

        return categories;
    }
}
