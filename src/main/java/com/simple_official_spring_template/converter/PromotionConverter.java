package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.PromotionDTO;
import com.simple_official_spring_template.entity.PromotionEntity;
import org.springframework.stereotype.Component;

@Component
public class PromotionConverter {
    public PromotionEntity toEntity(PromotionDTO promotionDTO, PromotionEntity promotionEntity){
        if (promotionDTO.getId() != null){
            promotionEntity.setId(promotionDTO.getId());
        }
        if (promotionDTO.getName() != null){
            promotionEntity.setName(promotionDTO.getName());
        }
        if (promotionDTO.getCode() != null){
            promotionEntity.setCode(promotionDTO.getCode());
        }
        if (promotionDTO.getRemainingNumber() != null){
            promotionEntity.setRemainingNumber(promotionDTO.getRemainingNumber());
        }
        if (promotionDTO.getValidityPeriod() != null){
            promotionEntity.setValidityPeriod(promotionDTO.getValidityPeriod());
        }
        if (promotionDTO.getDiscountPercent() != null){
            promotionEntity.setDiscountPercent(promotionDTO.getDiscountPercent());
        }
        if (promotionDTO.getDiscountMoney() != null){
            promotionEntity.setDiscountMoney(promotionDTO.getDiscountMoney());
        }
        return promotionEntity;
    }

    public PromotionDTO toDTO(PromotionEntity promotionEntity){
        return new PromotionDTO(
                promotionEntity.getId(),
                promotionEntity.getName(),
                promotionEntity.getCode(),
                promotionEntity.getRemainingNumber(),
                promotionEntity.getValidityPeriod(),
                promotionEntity.getDiscountPercent(),
                promotionEntity.getDiscountMoney()
        );
    }
}
