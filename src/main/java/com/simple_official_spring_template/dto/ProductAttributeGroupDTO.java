package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.entity.embeddedid.ProductAttributeGroupId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAttributeGroupDTO {
    private Integer attributeGroupId;
    private Integer productId;
    private Float addtionPrice;
    private ProductAttributeGroupId[] productAttributeGroupIds;

    public ProductAttributeGroupDTO() {
    }

    public ProductAttributeGroupDTO(Integer attributeGroupId, Integer productId, Float addtionPrice) {
        this.attributeGroupId = attributeGroupId;
        this.productId = productId;
        this.addtionPrice = addtionPrice;
    }
}
