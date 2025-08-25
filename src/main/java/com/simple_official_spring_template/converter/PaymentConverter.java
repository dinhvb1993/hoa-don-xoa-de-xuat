package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.PaymentDTO;
import com.simple_official_spring_template.entity.PaymentEntity;
import com.simple_official_spring_template.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {

    @Autowired
    private IOrderRepository orderRepository;

    public PaymentEntity toEntity(PaymentDTO paymentDTO, PaymentEntity paymentEntity){
//        if (paymentDTO.getOrderId() != null){
//            paymentEntity.setOrderId(paymentDTO.getOrderId());
//            paymentEntity.setOrder(orderRepository.findOneById(paymentDTO.getOrderId()));
//        }
        if (paymentDTO.getToken() != null){
            paymentEntity.setToken(paymentDTO.getToken());
        }
        if (paymentDTO.getPurchased() != null){
            paymentEntity.setPurchased(paymentDTO.getPurchased());
        }
        if (paymentDTO.getTime() != null){
            paymentEntity.setTime(paymentDTO.getTime());
        }
        return paymentEntity;
    }

    public PaymentDTO toDTO(PaymentEntity paymentEntity){
        return new PaymentDTO(
                paymentEntity.getOrderId(),
                paymentEntity.getToken(),
                paymentEntity.getPurchased(),
                paymentEntity.getTime()
        );
    }
}
