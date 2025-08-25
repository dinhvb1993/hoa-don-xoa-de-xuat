package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupOfProductDTO {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String categoryName;
    private Integer[] ids;

    public GroupOfProductDTO(Integer id, String name, Integer categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
