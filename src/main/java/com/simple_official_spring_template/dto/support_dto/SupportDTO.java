package com.simple_official_spring_template.dto.support_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportDTO {
    private String stringItem;
    private String[] stringList;
    private Long customerId;
    private String newName;
    private Long[] longIds;
}
