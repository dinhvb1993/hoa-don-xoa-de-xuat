package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OrderStatusDTO extends GenericStatusDTO {

    private Integer orderId;

    public OrderStatusDTO() {
    }

    public OrderStatusDTO(Integer id, com.simple_official_spring_template.dto.StatusDTO statusDTO, Timestamp time, Integer orderId) {
        super(id, statusDTO, time);
        this.orderId = orderId;
    }
}
