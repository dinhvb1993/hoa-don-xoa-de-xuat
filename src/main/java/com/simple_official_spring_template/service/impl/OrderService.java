package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.OrderConverter;
import com.simple_official_spring_template.dto.OrderDTO;
import com.simple_official_spring_template.entity.OrderEntity;
import com.simple_official_spring_template.entity.OrderStatusEntity;
import com.simple_official_spring_template.repository.IOrderRepository;
import com.simple_official_spring_template.repository.IOrderStatusRepository;
import com.simple_official_spring_template.repository.IStatusRepository;
import com.simple_official_spring_template.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private IOrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        List<OrderEntity> orderEntities = orderRepository.findAll();
        for (OrderEntity orderEntity: orderEntities) {
            orderDTOS.add(orderConverter.toDTO(orderEntity));
        }

        return orderDTOS;
    }

    @Override
    public List<OrderDTO> findByUserIdAndCurrentStatusId(String customerId, String currentOrderStatusId) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<OrderEntity> orderEntities;

        if (currentOrderStatusId == null) {
            orderEntities = orderRepository.findByUserId(customerId);
        }
        else {
            orderEntities = orderRepository.findByUserIdAndCurrentStatusId(customerId, currentOrderStatusId);
        }
        for (OrderEntity orderEntity: orderEntities) {
            orderDTOS.add(orderConverter.toDTO(orderEntity));
        }

        return orderDTOS;
    }

    @Override
    public List<OrderDTO> findByCurrentStatus(String currentOrderStatusId) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        List<OrderEntity> orderEntities = orderRepository.findByCurrentStatusId(currentOrderStatusId);
        for (OrderEntity orderEntity: orderEntities) {
            orderDTOS.add(orderConverter.toDTO(orderEntity));
        }

        return orderDTOS;
    }

    @Override
    public OrderDTO findOneById(Integer id) {
        OrderEntity orderEntity = orderRepository.findOneById(id);
        return orderConverter.toDTO(orderEntity);
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        OrderEntity orderEntity = orderConverter.toEntity(orderDTO, new OrderEntity());
        orderEntity.setFinalPrice(0.0);
        orderEntity = orderRepository.save(orderEntity);

        OrderStatusEntity orderStatusEntity = new OrderStatusEntity();
        orderStatusEntity.setOrder(orderEntity);
        orderStatusEntity.setStatus(statusRepository.findOneById(orderEntity.getCurrentStatusId()));
        orderStatusRepository.save(orderStatusEntity);

        return orderConverter.toDTO(orderEntity);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        OrderEntity orderEntity = orderRepository.findOneById(orderDTO.getId());
        orderEntity = orderConverter.toEntity(orderDTO, orderEntity);
        orderEntity = orderRepository.save(orderEntity);
        return orderConverter.toDTO(orderEntity);
    }
}
