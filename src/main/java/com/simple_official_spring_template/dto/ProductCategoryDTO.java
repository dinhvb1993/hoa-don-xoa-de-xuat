package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.entity.embeddedid.ProductCategoryId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryDTO {
    private Integer categoryId;
    private Integer productId;
    private ProductCategoryId[] productCategoryIds;

    public ProductCategoryDTO() {
    }

    public ProductCategoryDTO(Integer categoryId, Integer productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }
}
