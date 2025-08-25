package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.OrderStatusDTO;
import com.simple_official_spring_template.entity.OrderStatusEntity;
import com.simple_official_spring_template.repository.IOrderRepository;
import com.simple_official_spring_template.repository.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusConverter {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private com.simple_official_spring_template.converter.StatusConverter statusConverter;
    
    public OrderStatusEntity toEntity(OrderStatusDTO orderStatusDTO, OrderStatusEntity orderStatusEntity) {
        if (orderStatusDTO.getId() != null) {
            orderStatusEntity.setId(orderStatusDTO.getId());
        }
        if (orderStatusDTO.getOrderId() != null) {
            orderStatusEntity.setOrder(orderRepository.findOneById(orderStatusDTO.getOrderId()));
        }
        if (orderStatusDTO.getStatusDTO() != null) {
            orderStatusEntity.setStatus(statusRepository.findOneById(orderStatusDTO.getStatusDTO().getId()));
        }
        if (orderStatusDTO.getTime() != null) {
            orderStatusEntity.setTime(orderStatusDTO.getTime());
        }
        return orderStatusEntity;
    }

    public OrderStatusDTO toDTO(OrderStatusEntity orderStatusEntity) {
        return new OrderStatusDTO(
                orderStatusEntity.getId(),
                statusConverter.toDTO(orderStatusEntity.getStatus()),
                orderStatusEntity.getTime(),
                orderStatusEntity.getOrder().getId()
        );
    }
}
