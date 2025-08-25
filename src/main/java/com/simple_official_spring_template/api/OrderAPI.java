package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.OrderDTO;
import com.simple_official_spring_template.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderAPI {

    @Autowired
    private IOrderService orderService;

    @GetMapping(value = "/api-order")
    public List<OrderDTO> getOrders(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "currentStatusId", required = false) String currentStatusId
            ) {
        if (userId != null) {
            return orderService.findByUserIdAndCurrentStatusId(userId, currentStatusId);
        }

        return orderService.findAll();
    }

    @GetMapping(value = "/api-order/{id}")
    public OrderDTO getOrderById(@PathVariable(name = "id", required = true) Integer id) {
        return orderService.findOneById(id);
    }

    @PostMapping(value = "/api-order")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    @PutMapping(value = "/api-order")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.update(orderDTO);
    }
}
