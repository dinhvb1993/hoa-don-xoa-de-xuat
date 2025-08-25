package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDTO extends GenericAddressDTO {
    private String userId;

    public UserAddressDTO(Integer id, String street, WardDTO wardDTO, String userId) {
        super(id, street, wardDTO);
        this.userId = userId;
    }
}
