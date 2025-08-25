package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.PaymentDTO;
import com.simple_official_spring_template.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentAPI {
    @Autowired
    private IPaymentService paymentService;

    @GetMapping(value = "/api-payment/{orderId}")
    public PaymentDTO getPaymentById(@PathVariable(value = "orderId", required = true) Integer orderId){
        return paymentService.findOneByOrderId(orderId);
    }

    @GetMapping(value = "/api-payment")
    public List<PaymentDTO> getPayments() {
        return paymentService.findAll();
    }

    @GetMapping(value = "/api-payment-token")
    public PaymentDTO getPaymentByToken(@RequestParam(value = "token") String token) {
        return paymentService.findOneByToken(token);
    }


    @PostMapping(value = "/api-payment")
    public PaymentDTO createPayment(@RequestBody PaymentDTO paymentDTO){
        return paymentService.save(paymentDTO);
    }

    @PutMapping(value = "/api-payment")
    public PaymentDTO updatePayment(@RequestBody PaymentDTO paymentDTO){
        return paymentService.update(paymentDTO);
    }
}
