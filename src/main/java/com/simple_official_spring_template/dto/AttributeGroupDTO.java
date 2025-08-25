package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class AttributeGroupDTO extends com.simple_official_spring_template.dto.GenericCategoryDTO {

    private AttributeGroupDTO parentAttributeGroupDTO;
    private ImageSliderItemDTO imageSliderAvatarItemDTO;
    private List<ImageSliderItemDTO> imageSliderItemDTOS;

    public AttributeGroupDTO() {
    }

    public AttributeGroupDTO(Integer id, String name, String slug, Integer orderNumber, String description, String compareFeild, Boolean showHome, Timestamp creationTime, Timestamp updateTime, int degree, AttributeGroupDTO parentAttributeGroupDTO, ImageSliderItemDTO imageSliderAvatarItemDTO, List<ImageSliderItemDTO> imageSliderItemDTOS) {
        super(id, name, slug, orderNumber, description, compareFeild, showHome, creationTime, updateTime, degree);
        this.parentAttributeGroupDTO = parentAttributeGroupDTO;
        this.imageSliderAvatarItemDTO = imageSliderAvatarItemDTO;
        this.imageSliderItemDTOS = imageSliderItemDTOS;
    }
}
