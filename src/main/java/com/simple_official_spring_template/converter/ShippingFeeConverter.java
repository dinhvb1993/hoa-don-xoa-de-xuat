package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ShippingFeeDTO;
import com.simple_official_spring_template.entity.ShippingFeeEntity;
import org.springframework.stereotype.Component;

@Component
public class ShippingFeeConverter {

    public ShippingFeeEntity toEntity(ShippingFeeDTO shippingFeeDTO, ShippingFeeEntity shippingFeeEntity){
        if (shippingFeeDTO.getId() != null){
            shippingFeeEntity.setId(shippingFeeDTO.getId());
        }
        if (shippingFeeDTO.getInternalCity() != null){
            shippingFeeEntity.setInternalCity(shippingFeeDTO.getInternalCity());
        }
        if (shippingFeeDTO.getMinWeight() != null){
            shippingFeeEntity.setMinWeight(shippingFeeDTO.getMinWeight());
        }
        if (shippingFeeDTO.getMaxWeight() != null){
            shippingFeeEntity.setMaxWeight(shippingFeeDTO.getMaxWeight());
        }
        if (shippingFeeDTO.getPrice() != null){
            shippingFeeEntity.setPrice(shippingFeeDTO.getPrice());
        }
        if (shippingFeeDTO.getAdditionalPrice() != null){
            shippingFeeEntity.setAdditionalPrice(shippingFeeDTO.getAdditionalPrice());
        }
        return shippingFeeEntity;
    }

    public ShippingFeeDTO toDTO(ShippingFeeEntity shippingFeeEntity){
        return new ShippingFeeDTO(
                shippingFeeEntity.getId(),
                shippingFeeEntity.getInternalCity(),
                shippingFeeEntity.getMinWeight(),
                shippingFeeEntity.getMaxWeight(),
                shippingFeeEntity.getPrice(),
                shippingFeeEntity.getAdditionalPrice()
        );
    }
}
