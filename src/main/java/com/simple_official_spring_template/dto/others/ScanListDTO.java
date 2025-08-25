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
public class ScanListDTO {
    private String link;
    private String type;
    private Timestamp updateTime;
    private Long loginCustomerId;
}
