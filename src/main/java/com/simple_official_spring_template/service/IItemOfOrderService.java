package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ItemOfOrderDTO;
import com.simple_official_spring_template.entity.embeddedid.ItemOfOrderId;

import java.util.List;

public interface IItemOfOrderService {
    List<ItemOfOrderDTO> findAll();
    List<ItemOfOrderDTO> findByOrderId(Integer orderId);
    ItemOfOrderDTO findOneByItemOfOrderId(ItemOfOrderId itemOfOrderId);
    ItemOfOrderDTO save(ItemOfOrderDTO itemOfOrderDTO);
    ItemOfOrderDTO update(ItemOfOrderDTO itemOfOrderDTO);
    void delete(List<ItemOfOrderDTO> itemOfOrderDTOS);
    List<ItemOfOrderDTO> findByUserIdAndProductId(String userId, Integer productId);
}
