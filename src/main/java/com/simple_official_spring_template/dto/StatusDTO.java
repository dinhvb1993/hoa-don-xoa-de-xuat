package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDTO {
    private String id;
    private String name;
    private Integer[] ids;

    public StatusDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
