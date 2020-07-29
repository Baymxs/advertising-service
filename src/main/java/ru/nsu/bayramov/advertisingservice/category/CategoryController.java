package ru.nsu.bayramov.advertisingservice.category;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nsu.bayramov.advertisingservice.model.dto.CategoryDto;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    @ApiOperation(value = "Создание новой категории")
    public void create(
            @ApiParam(required = true, value = "Информация о категории")
            @RequestBody
                    CategoryDto categoryDto) {
        categoryService.create(categoryDto);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Редактирование категории")
    public void update(
            @ApiParam(required = true, value = "Информация о категории")
            @RequestBody
                    CategoryDto categoryDto) {
        categoryService.update(categoryDto);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Поиск категории")
    public List<CategoryDto> search(
            @ApiParam(required = true, value = "Строка поиска")
            @RequestParam
                    String searchString) {
        return categoryService.search(searchString);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление категории")
    public List<Integer> delete(
            @ApiParam(required = true, value = "id категории")
            @RequestParam
                    int categoryId) {
        return categoryService.delete(categoryId);
    }
}
