package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.entity.embeddedid.ProductAttributeGroupId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_attribute_group")
public class ProductAttributeGroupEntity {
    @EmbeddedId
    private ProductAttributeGroupId productAttributeGroupId;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private com.simple_official_spring_template.entity.ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "attributeGroupId", insertable = false, updatable = false)
    private com.simple_official_spring_template.entity.AttributeGroupEntity attributeGroup;

    @Column
    private Float additionPrice;
}
