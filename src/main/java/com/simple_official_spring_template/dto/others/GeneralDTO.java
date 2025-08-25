package com.simple_official_spring_template.dto.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralDTO {
    private String id;
    private String value;
    private String value2;
    private Timestamp updateTime;
}
