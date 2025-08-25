package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "shippingFee")
public class ShippingFeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private Boolean internalCity;

    @Column
    private Float minWeight;

    @Column
    private Float maxWeight;

    @Column
    private Float price;

    @Column
    private Float additionalPrice;
}
