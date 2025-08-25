package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingFeeDTO {
    private Integer id;
    private Boolean internalCity;
    private Float minWeight;
    private Float maxWeight;
    private Float price;
    private Float additionalPrice;
    private Float currentWeight;
    private Float totalPrice;
    private Integer[] ids;

    public ShippingFeeDTO() {
    }

    public ShippingFeeDTO(Integer id, Boolean internalCity, Float minWeight, Float maxWeight, Float price, Float additionalPrice) {
        this.id = id;
        this.internalCity = internalCity;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.price = price;
        this.additionalPrice = additionalPrice;
    }
}
