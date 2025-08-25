package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "store_request")
public class StoreRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_of_shop_id")
    private ProductEntity product;

    @Column(nullable = false)
    private Timestamp time;

    @Column(nullable = false)
    private Integer quantity;
}
