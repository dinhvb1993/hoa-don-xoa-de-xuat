package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GenericAddressDTO {
    private Integer id;
    private String street;
    private WardDTO wardDTO;
    private Integer[] ids;

    public GenericAddressDTO(Integer id, String street, WardDTO wardDTO) {
        this.id = id;
        this.street = street;
        this.wardDTO = wardDTO;
    }
}
