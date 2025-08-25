package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class ProductTypeDTO extends com.simple_official_spring_template.dto.GenericCategoryDTO {

    private Integer parentId;
    private ImageSliderItemDTO imageSliderAvatarItemDTO;
    private List<ImageSliderItemDTO> imageSliderItemDTOS;

    public ProductTypeDTO() {
    }

    public ProductTypeDTO(Integer id, String name, String slug, Integer orderNumber, String description, String compareFeild, Boolean showHome, Timestamp creationTime, Timestamp updateTime, int degree, Integer parentId, ImageSliderItemDTO imageSliderAvatarItemDTO, List<ImageSliderItemDTO> imageSliderItemDTOS) {
        super(id, name, slug, orderNumber, description, compareFeild, showHome, creationTime, updateTime, degree);
        this.parentId = parentId;
        this.imageSliderAvatarItemDTO = imageSliderAvatarItemDTO;
        this.imageSliderItemDTOS = imageSliderItemDTOS;
    }
}
