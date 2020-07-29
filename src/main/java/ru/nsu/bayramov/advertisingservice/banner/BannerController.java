package ru.nsu.bayramov.advertisingservice.banner;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nsu.bayramov.advertisingservice.model.dto.BannerCreationDto;
import ru.nsu.bayramov.advertisingservice.model.dto.BannerDto;
import ru.nsu.bayramov.advertisingservice.model.dto.ContentDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/banner")
@RequiredArgsConstructor
@Slf4j
public class BannerController {
    private final BannerService bannerService;

    @PostMapping("/create")
    @ApiOperation(value = "Создание нового баннера")
    public void create(
            @ApiParam(required = true, value = "Информация о баннере")
            @RequestBody
                    BannerCreationDto bannerCreationDto) {
        bannerService.create(bannerCreationDto);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Редактирование баннера")
    public void update(
            @ApiParam(required = true, value = "Информация о баннере")
            @RequestBody
                    BannerDto bannerDto) {
        bannerService.update(bannerDto);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Поиск баннера")
    public List<BannerDto> search(
            @ApiParam(required = true, value = "Строка поиска")
            @RequestParam
                    String searchString) {
        return bannerService.search(searchString);
    }

    @GetMapping("/get")
    @ApiOperation(value = "Получение текста баннера")
    public ContentDto get(
            @ApiParam(required = true, value = "Категория")
            @RequestParam
                    String categoryReqName,
            @RequestHeader("User-Agent")
                    String userAgent, HttpServletRequest httpServletRequest) {
        return bannerService.getBannerContent(categoryReqName, httpServletRequest.getRemoteAddr(), userAgent);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление баннера")
    public void delete(
            @ApiParam(required = true, value = "id баннера")
            @RequestParam
                    int bannerId) {
        bannerService.delete(bannerId);
    }
}
