package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDTO {
    private Integer id;
    private String name;
    private Integer[] ids;

    public BankDTO() {
    }

    public BankDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
