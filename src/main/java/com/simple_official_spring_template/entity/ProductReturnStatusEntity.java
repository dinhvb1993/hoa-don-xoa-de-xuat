package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_return_status")
public class ProductReturnStatusEntity extends GenericStatus {
    @ManyToOne
    @JoinColumn(name = "product_return_id")
    private com.simple_official_spring_template.entity.ProductReturnEntity productReturn;

    @OneToMany(mappedBy = "productReturnStatus")
    private Set<com.simple_official_spring_template.entity.ReturnedProductEntity> returnedVariantProducts;
}
