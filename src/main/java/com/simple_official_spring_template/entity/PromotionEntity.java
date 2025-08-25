package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "promotion")
public class PromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column
    private Integer remainingNumber;

    @Column
    private Timestamp validityPeriod;

    @Column
    private Integer discountPercent;

    @Column
    private Float discountMoney;


    @OneToMany(mappedBy = "promotion")
    private Set<ProductEntity> products;

}
