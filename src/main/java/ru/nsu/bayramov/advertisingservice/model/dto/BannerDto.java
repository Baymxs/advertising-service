package ru.nsu.bayramov.advertisingservice.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel(description = "Баннер")
public class BannerDto {
    @ApiModelProperty(value = "Id", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "Название баннера", required = true, example = "Мячи для гольфа")
    private String name;

    @ApiModelProperty(value = "Цена", required = true, example = "99.00")
    private BigDecimal price;

    @ApiModelProperty(value = "Категория баннера")
    private CategoryDto category;

    @ApiModelProperty(value = "Текст баннера", required = true, example = "Покупайте мячи для гольфа только у нас!")
    private String content;
}
