package com.simple_official_spring_template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemOfOrderDTO {
    private ProductDTO productDTO;
    private Integer orderId;
    private Integer quantity;
    private Double priceOfItem;
    private String promotionCode;
    private Integer applyingNumber;
    private Double deduction;
    private Double finalPrice;
    private Integer errorCode;
}
