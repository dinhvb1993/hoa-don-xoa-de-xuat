package com.simple_official_spring_template.entity.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProductCategoryId implements Serializable {
    private Integer productId;
    private Integer categoryId;

    public ProductCategoryId() {
    }

    public ProductCategoryId(Integer productId, Integer categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }
}
