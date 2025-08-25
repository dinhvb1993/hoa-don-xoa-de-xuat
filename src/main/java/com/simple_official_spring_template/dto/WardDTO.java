package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WardDTO {
    private Integer id;
    private String name;
    private DistrictDTO districtDTO;
    private Integer[] ids;

    public WardDTO() {
    }

    public WardDTO(Integer id, String name, DistrictDTO districtDTO) {
        this.id = id;
        this.name = name;
        this.districtDTO = districtDTO;
    }
}
