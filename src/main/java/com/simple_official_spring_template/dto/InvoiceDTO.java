package com.simple_official_spring_template.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceDTO {
    private String id;
    private String fullDate;
    private Double money;
    private String originalLink;
    private String customerId;
    private String customerName;
    private String fileUrl;

    private String year;
    private String month;
    private String date;

    private String[] ids;
}
