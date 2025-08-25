package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PromotionDTO {
    private Integer id;
    private String name;
    private String code;
    private Integer remainingNumber;
    private Timestamp validityPeriod;
    private Integer discountPercent;
    private Float discountMoney;
    private Integer[] ids;

    public PromotionDTO() {
    }

    public PromotionDTO(Integer id, String name, String code, Integer remainingNumber, Timestamp validityPeriod, Integer discountPercent, Float discountMoney) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.remainingNumber = remainingNumber;
        this.validityPeriod = validityPeriod;
        this.discountPercent = discountPercent;
        this.discountMoney = discountMoney;
    }

}
