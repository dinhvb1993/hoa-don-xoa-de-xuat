package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.entity.embeddedid.ProductCategoryId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_category")
public class ProductCategoryEntity {
    @EmbeddedId
    private ProductCategoryId productCategoryId;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private CategoryEntity category;
}
