package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ItemOfOrderDTO;
import com.simple_official_spring_template.entity.ItemOfOrderEntity;
import com.simple_official_spring_template.entity.embeddedid.ItemOfOrderId;
import com.simple_official_spring_template.repository.IOrderRepository;
import com.simple_official_spring_template.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemOfOrderConverter {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ProductConverter productConverter;
    
    public ItemOfOrderEntity toEntity(ItemOfOrderDTO itemOfOrderDTO, ItemOfOrderEntity itemOfOrderEntity) {
        if (itemOfOrderDTO.getProductDTO() != null) {
            itemOfOrderEntity.setProduct(productRepository.findOneById(itemOfOrderDTO.getProductDTO().getId()));

            itemOfOrderEntity.setItemOfOrderId(new ItemOfOrderId(itemOfOrderDTO.getProductDTO().getId(), itemOfOrderDTO.getOrderId()));
        }
        if (itemOfOrderDTO.getOrderId() != null) {
            itemOfOrderEntity.setOrder(orderRepository.findOneById(itemOfOrderDTO.getOrderId()));
        }
        if (itemOfOrderDTO.getQuantity() != null) {
            itemOfOrderEntity.setQuantity(itemOfOrderDTO.getQuantity());
        }
        if (itemOfOrderDTO.getPriceOfItem() != null) {
            itemOfOrderEntity.setPriceOfItem(itemOfOrderDTO.getPriceOfItem());
        }
        if (itemOfOrderDTO.getPromotionCode() != null) {
            itemOfOrderEntity.setPromotionCode(itemOfOrderDTO.getPromotionCode());
        }
        if (itemOfOrderDTO.getApplyingNumber() != null) {
            itemOfOrderEntity.setApplyingNumber(itemOfOrderDTO.getApplyingNumber());
        }
        if (itemOfOrderDTO.getDeduction() != null) {
            itemOfOrderEntity.setDeduction(itemOfOrderDTO.getDeduction());
        }
        if (itemOfOrderDTO.getFinalPrice() != null) {
            itemOfOrderEntity.setFinalPrice(itemOfOrderDTO.getFinalPrice());
        }
        if (itemOfOrderDTO.getErrorCode() != null) {
            itemOfOrderEntity.setErrorCode(itemOfOrderDTO.getErrorCode());
        }

        return itemOfOrderEntity;
    }

    public ItemOfOrderDTO toDTO(ItemOfOrderEntity itemOfOrderEntity) {
        return new ItemOfOrderDTO(
                productConverter.toDTO(itemOfOrderEntity.getProduct()),
                itemOfOrderEntity.getOrder().getId(),
                itemOfOrderEntity.getQuantity(),
                itemOfOrderEntity.getPriceOfItem(),
                itemOfOrderEntity.getPromotionCode(),
                itemOfOrderEntity.getApplyingNumber(),
                itemOfOrderEntity.getDeduction(),
                itemOfOrderEntity.getFinalPrice(),
                itemOfOrderEntity.getErrorCode()
        );
    }
}
