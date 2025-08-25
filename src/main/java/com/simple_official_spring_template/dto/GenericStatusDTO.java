package com.simple_official_spring_template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericStatusDTO {

    private Integer id;
    private com.simple_official_spring_template.dto.StatusDTO statusDTO;
    private Timestamp time;

}
