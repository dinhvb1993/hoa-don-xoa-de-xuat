package com.simple_official_spring_template.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "City Model")
public class CityDTO {
    private Integer id;
    private String name;
    private Integer[] ids;

    public CityDTO() {
    }

    public CityDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
