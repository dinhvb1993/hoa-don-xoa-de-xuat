package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.entity.embeddedid.ProductProductTypeId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_product_type")
public class ProductProductTypeEntity {

    @EmbeddedId
    private ProductProductTypeId productProductTypeId;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "productTypeId", insertable = false, updatable = false)
    private ProductTypeEntity productType;

}
