package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.PaymentDTO;

import java.util.List;

public interface IPaymentService {
    PaymentDTO save(PaymentDTO paymentDTO);
    PaymentDTO update(PaymentDTO paymentDTO);
    PaymentDTO findOneByOrderId(Integer orderId);
    PaymentDTO findOneByToken(String token);
    List<PaymentDTO> findAll();
    void delete(PaymentDTO paymentDTO);
}
