package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PaymentDTO {

    private Integer orderId;
    private String token;
    private Boolean purchased;
    private Timestamp time;
    Integer[] ids;

    public PaymentDTO(Integer orderId, String token, Boolean purchased, Timestamp time) {
        this.orderId = orderId;
        this.token = token;
        this.purchased = purchased;
        this.time = time;
    }
}
