package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "invoice_ori_link")
public class InvoiceOriLinkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String link;

    @Column
    private String customerId;

    @Column(columnDefinition = "TEXT")
    private String customerName;
}
