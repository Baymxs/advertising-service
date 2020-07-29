package ru.nsu.bayramov.advertisingservice.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@ApiModel(description = "Текст баннера")
public class ContentDto {
    @ApiModelProperty(value = "Текст баннера", required = true, example = "Покупайте мячи для гольфа только у нас!")
    private final String content;
}
