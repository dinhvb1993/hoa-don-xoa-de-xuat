package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.OrderStatusDTO;
import com.simple_official_spring_template.service.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderStatusAPI {

    @Autowired
    private IOrderStatusService orderStatusService;

    @GetMapping(value = "/api-order-status")
    public List<OrderStatusDTO> getOrderStatuses(@RequestParam(name = "orderId", required = false) Integer orderId) {
        if (orderId != null) {
            return orderStatusService.findByOrderId(orderId);
        }
        else {
            return orderStatusService.findAll();
        }
    }

    @GetMapping(value = "/api-order-status/{id}")
    public OrderStatusDTO getOrderStatusById(@PathVariable(name = "id", required = true) Integer id) {
        return orderStatusService.findOneById(id);
    }

    // Create order status (!= ORDER_APPROVED)
    @PostMapping(value = "/api-order-status")
    public OrderStatusDTO createOrderStatus(@RequestBody OrderStatusDTO orderStatusDTO) {
        return orderStatusService.save(orderStatusDTO);
    }

    @PutMapping(value = "/api-order-status")
    public OrderStatusDTO updateOrderStatus(@RequestBody OrderStatusDTO orderStatusDTO) {
        return orderStatusService.update(orderStatusDTO);
    }
}
