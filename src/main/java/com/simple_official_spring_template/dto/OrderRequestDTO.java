package com.simple_official_spring_template.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    private com.simple_official_spring_template.dto.ItemOfOrderDTO itemOfOrderDTO;
    private String action;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(com.simple_official_spring_template.dto.ItemOfOrderDTO itemOfOrderDTO, String action) {
        this.itemOfOrderDTO = itemOfOrderDTO;
        this.action = action;
    }
}
