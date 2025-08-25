package com.simple_official_spring_template.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    private String id;

    @Column
    private String fullDate;

    @Column
    private Double money;


    @Column(columnDefinition = "TEXT")
    private String originalLink;

    @Column
    private String customerId;

    @Column
    private String customerName;

    @Column(columnDefinition = "TEXT")
    private String fileUrl;

    @Column
    private String year;

    @Column
    private String month;

    @Column
    private String date;
}
