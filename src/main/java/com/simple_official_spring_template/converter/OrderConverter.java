package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.OrderDTO;
import com.simple_official_spring_template.entity.OrderEntity;
import com.simple_official_spring_template.repository.IStatusRepository;
import com.simple_official_spring_template.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private OrderStatusConverter orderStatusConverter;

    @Autowired
    private com.simple_official_spring_template.converter.StatusConverter statusConverter;

    @Autowired
    private IStatusRepository statusRepository;

    
    public OrderEntity toEntity(OrderDTO orderDTO, OrderEntity orderEntity) {
        if (orderDTO.getId() != null) {
            orderEntity.setId(orderDTO.getId());
        }
        if (orderDTO.getUserId() != null) {
            orderEntity.setUser(userRepository.findOneById(orderDTO.getUserId()));
        }
        if (orderDTO.getTotalPrice() != null) {
            orderEntity.setTotalPrice(orderDTO.getTotalPrice());
        }
        if (orderDTO.getTotalWeight() != null) {
            orderEntity.setTotalWeight(orderDTO.getTotalWeight());
        }
        if (orderDTO.getStatusDTO() != null) {
            orderEntity.setCurrentStatusId(orderDTO.getStatusDTO().getId());
        }
        if (orderDTO.getPaymentTime() != null) {
            orderEntity.setPaymentTime(orderDTO.getPaymentTime());
        }
        if (orderDTO.getShippingFee() != null) {
            orderEntity.setShippingFee(orderDTO.getShippingFee());
        }
        if (orderDTO.getFinalPrice() != null) {
            orderEntity.setFinalPrice(orderDTO.getFinalPrice());
        }
        if (orderDTO.getIsAvailable() != null) {
            orderEntity.setIsAvailable(orderDTO.getIsAvailable());
        }
        return orderEntity;
    }

    public OrderDTO toDTO(OrderEntity orderEntity) {
        return new OrderDTO(
                orderEntity.getId(),
                orderEntity.getUser().getId(),
                orderEntity.getTotalPrice(),
                orderEntity.getTotalWeight(),
                orderEntity.getCurrentStatusId() != null ? statusConverter.toDTO(statusRepository.findOneById(orderEntity.getCurrentStatusId())): null,
                orderEntity.getPaymentTime(),
                orderEntity.getShippingFee(),
                orderEntity.getFinalPrice(),
                orderEntity.getIsAvailable()
        );
    }
}
