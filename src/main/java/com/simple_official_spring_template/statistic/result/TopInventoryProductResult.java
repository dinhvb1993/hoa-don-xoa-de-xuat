package com.simple_official_spring_template.statistic.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopInventoryProductResult {

    private String productId;
    private Integer quantity;
}
