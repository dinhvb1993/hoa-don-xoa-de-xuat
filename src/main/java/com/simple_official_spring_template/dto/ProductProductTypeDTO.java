package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.entity.embeddedid.ProductProductTypeId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductProductTypeDTO {
    private Integer productTypeId;
    private Integer productId;
    private ProductProductTypeId[] productProductTypeIds;

    public ProductProductTypeDTO() {
    }

    public ProductProductTypeDTO(Integer productTypeId, Integer productId) {
        this.productTypeId = productTypeId;
        this.productId = productId;
    }
}
