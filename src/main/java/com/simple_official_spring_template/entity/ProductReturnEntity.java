package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_return")
public class ProductReturnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private com.simple_official_spring_template.entity.OrderEntity order;

    @OneToMany(mappedBy = "productReturn")
    private Set<ProductReturnStatusEntity> productReturnStatuses;

    @OneToMany(mappedBy = "productReturn")
    private Set<com.simple_official_spring_template.entity.ReturnedProductEntity> returnedVariantProducts;
}
