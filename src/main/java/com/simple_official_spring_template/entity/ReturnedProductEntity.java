package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "returned_product_of_shop")
public class ReturnedProductEntity {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_return_id")
    private ProductReturnEntity productReturn;

    @ManyToOne
    @JoinColumn(name = "product_return_status_id")
    private ProductReturnStatusEntity productReturnStatus;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Integer quantity;

    @OneToMany(mappedBy = "returnedVariantProduct")
    private Set<ProductReturnImageSliderItemEntity> productReturnImageSliderItems;
}
