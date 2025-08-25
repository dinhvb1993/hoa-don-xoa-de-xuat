package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GenericImageSliderItemDTO {
    private Integer id;
    private String url;
    private String title;
    private String link;
    private String caption;
    private Integer[] ids;

    public GenericImageSliderItemDTO(Integer id, String url, String title, String link, String caption) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.link = link;
        this.caption = caption;
    }
}
