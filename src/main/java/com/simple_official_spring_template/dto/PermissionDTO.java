package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PermissionDTO {
    private Integer id;
    private String name;
    private Integer[] ids;

    public PermissionDTO() {
    }

    public PermissionDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
