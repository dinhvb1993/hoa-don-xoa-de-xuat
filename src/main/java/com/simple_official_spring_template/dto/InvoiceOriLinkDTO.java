package com.simple_official_spring_template.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceOriLinkDTO {
    private Integer id;
    private String link;
    private Integer[] ids;
}
