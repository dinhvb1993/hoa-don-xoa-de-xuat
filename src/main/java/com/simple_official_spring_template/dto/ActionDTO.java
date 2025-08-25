package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionDTO {
    private Integer id;
    private String name;
    private Integer[] ids;

    public ActionDTO() {
    }

    public ActionDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
