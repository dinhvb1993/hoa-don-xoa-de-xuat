package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictDTO {
    private Integer id;
    private String name;
    private com.simple_official_spring_template.dto.CityDTO cityDTO;
    private Integer[] ids;

    public DistrictDTO() {
    }

    public DistrictDTO(Integer id, String name, com.simple_official_spring_template.dto.CityDTO cityDTO) {
        this.id = id;
        this.name = name;
        this.cityDTO = cityDTO;
    }
}
