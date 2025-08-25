package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.OrderStatusDTO;

import java.util.List;

public interface IOrderStatusService {
    List<OrderStatusDTO> findAll();
    List<OrderStatusDTO> findByOrderId(Integer orderId);
    OrderStatusDTO findOneById(Integer id);
    OrderStatusDTO save(OrderStatusDTO orderStatusDTO);
    OrderStatusDTO update(OrderStatusDTO orderStatusDTO);
}
