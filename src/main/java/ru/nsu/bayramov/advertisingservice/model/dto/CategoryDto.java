package ru.nsu.bayramov.advertisingservice.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Категория")
public class CategoryDto {
    @ApiModelProperty(value = "Id", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "Название категории", required = true, example = "Спорт")
    private String name;

    @ApiModelProperty(value = "Название категории", required = true, example = "Спорт")
    private String requestName;

    @ApiModelProperty(value = "Отметка об удаление", required = true, example = "true")
    private Boolean deleted;
}
