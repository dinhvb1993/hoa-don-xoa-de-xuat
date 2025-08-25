package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> findAll();
    List<OrderDTO> findByUserIdAndCurrentStatusId(String customerId, String currentStatusId);
    List<OrderDTO> findByCurrentStatus(String currentOrderStatusId);
    OrderDTO findOneById(Integer id);
    OrderDTO save(OrderDTO orderDTO);
    OrderDTO update(OrderDTO orderDTO);
}
