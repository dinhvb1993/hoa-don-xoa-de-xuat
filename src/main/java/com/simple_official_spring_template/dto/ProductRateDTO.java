package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.entity.embeddedid.ProductRateId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRateDTO {
    private String userId;
    private Integer productId;
    private Double rate;
    private ProductRateId[] rateIds;

    public ProductRateDTO() {
    }

    public ProductRateDTO(String userId, Integer productId, Double rate) {
        this.userId = userId;
        this.productId = productId;
        this.rate = rate;
    }
}
