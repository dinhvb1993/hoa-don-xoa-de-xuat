package com.simple_official_spring_template.statistic.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopBestSellerProductResult {

    // In shop statistic, id means productOfShopId
    // In system statistic, id means productId
    private String id;
    private Integer finalQuantity;
    private Integer quantity;
    private Integer returnNumber;
    private Float finalPrice;
}
