package ru.nsu.bayramov.advertisingservice.dtotransformservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.nsu.bayramov.advertisingservice.model.domain.Banner;
import ru.nsu.bayramov.advertisingservice.model.domain.Category;
import ru.nsu.bayramov.advertisingservice.model.dto.BannerCreationDto;
import ru.nsu.bayramov.advertisingservice.model.dto.BannerDto;
import ru.nsu.bayramov.advertisingservice.model.dto.CategoryDto;

@Service
@RequiredArgsConstructor
public class DtoTransformService {
    private final ModelMapper modelMapper;

    public Banner convertToBanner(BannerCreationDto bannerCreationDto) {
        return modelMapper.map(bannerCreationDto, Banner.class);
    }

    public Banner convertToBanner(BannerDto bannerDto) {
        return modelMapper.map(bannerDto, Banner.class);
    }

    public Category convertToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    public BannerDto convertToBannerDto(Banner banner) {
        return modelMapper.map(banner, BannerDto.class);
    }

    public CategoryDto convertToCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
