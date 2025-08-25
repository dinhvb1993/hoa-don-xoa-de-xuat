package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.PaymentConverter;
import com.simple_official_spring_template.dto.PaymentDTO;
import com.simple_official_spring_template.entity.PaymentEntity;
import com.simple_official_spring_template.repository.IPaymentRepository;
import com.simple_official_spring_template.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private PaymentConverter paymentConverter;


    @Override
    public PaymentDTO save(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = paymentConverter.toEntity(paymentDTO, new PaymentEntity());
        return paymentConverter.toDTO(paymentRepository.save(paymentEntity));
    }

    @Override
    public PaymentDTO update(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = paymentRepository.findOneByOrderId(paymentDTO.getOrderId());
        paymentEntity = paymentConverter.toEntity(paymentDTO, paymentEntity);
        return paymentConverter.toDTO(paymentRepository.save(paymentEntity));
    }

    @Override
    public PaymentDTO findOneByOrderId(Integer orderId) {
        return paymentConverter.toDTO(paymentRepository.findOneByOrderId(orderId));
    }

    @Override
    public PaymentDTO findOneByToken(String token) {
        return paymentConverter.toDTO(paymentRepository.findOneByToken(token));
    }

    @Override
    public List<PaymentDTO> findAll() {
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (PaymentEntity paymentEntity: paymentRepository.findAll()){
            paymentDTOS.add(paymentConverter.toDTO(paymentEntity));
        }
        return paymentDTOS;
    }

    @Override
    public void delete(PaymentDTO paymentDTO) {
        paymentRepository.deleteByIds(paymentDTO.getIds());
    }
}
