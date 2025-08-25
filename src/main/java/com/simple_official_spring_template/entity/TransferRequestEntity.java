package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "transfer_request")
public class TransferRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_of_shop_id")
    private com.simple_official_spring_template.entity.ProductEntity product;

    @Column(nullable = false)
    private Timestamp time;

    @Column(nullable = false)
    private Integer quantity;
}
