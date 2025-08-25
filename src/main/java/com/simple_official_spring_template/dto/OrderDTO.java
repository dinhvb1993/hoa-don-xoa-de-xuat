package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OrderDTO {
    private Integer id;
    private String userId;
    private Double totalPrice;
    private Double totalWeight;
    private com.simple_official_spring_template.dto.StatusDTO statusDTO;
    private Timestamp paymentTime;
    private Double shippingFee;
    private Double finalPrice;
    private Boolean isAvailable;
    private Integer[] ids;

    public OrderDTO(Integer id, String userId, Double totalPrice, Double totalWeight, StatusDTO statusDTO, Timestamp paymentTime, Double shippingFee, Double finalPrice, Boolean isAvailable) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.statusDTO = statusDTO;
        this.paymentTime = paymentTime;
        this.shippingFee = shippingFee;
        this.finalPrice = finalPrice;
        this.isAvailable = isAvailable;
    }
}

