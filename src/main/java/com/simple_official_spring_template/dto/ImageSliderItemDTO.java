package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageSliderItemDTO extends com.simple_official_spring_template.dto.GenericImageSliderItemDTO {
//    private Integer productId;


    public ImageSliderItemDTO(Integer id, String url, String title, String link, String caption) {
        super(id, url, title, link, caption);
    }
}
