package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ItemOfOrderDTO;
import com.simple_official_spring_template.entity.embeddedid.ItemOfOrderId;
import com.simple_official_spring_template.service.IItemOfOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemOfOrderAPI {

    @Autowired
    private IItemOfOrderService itemOfOrderService;

    @GetMapping(value = "/api-item-of-order")
    public List<ItemOfOrderDTO> getItemOfOrders(@RequestParam(name = "orderId", required = false) Integer orderId) {
        if (orderId != null) {
            return itemOfOrderService.findByOrderId(orderId);
        }
        else {
            return itemOfOrderService.findAll();
        }
    }


    @GetMapping(value = "/api-item-of-order-by-user-id-and-product-id/{userId}/{productId}")
    public List<ItemOfOrderDTO> findByUserIdAnAndProductId(
            @PathVariable(name = "productId", required = true) Integer productId,
            @PathVariable(name = "userId", required = true) String userId)
    {
        return itemOfOrderService.findByUserIdAndProductId(userId, productId);
    }

    @GetMapping(value = "/api-item-of-order/{productId}/{orderId}")
    public ItemOfOrderDTO getItemOfOrderById(
            @PathVariable(name = "productId", required = true) Integer productId,
            @PathVariable(name = "orderId", required = true) Integer orderId) {
        return itemOfOrderService.findOneByItemOfOrderId(new ItemOfOrderId(productId, orderId));
    }

    @PostMapping(value = "/api-item-of-order")
    public ItemOfOrderDTO createItemOfOrder(@RequestBody ItemOfOrderDTO itemOfOrderDTO) {
        return itemOfOrderService.save(itemOfOrderDTO);
    }

    @PutMapping(value = "/api-item-of-order")
    public ItemOfOrderDTO updateItemOfOrder(@RequestBody ItemOfOrderDTO itemOfOrderDTO) {
        return itemOfOrderService.update(itemOfOrderDTO);
    }

    @DeleteMapping(value = "/api-item-of-order")
    public void deleteItemOfOrder(@RequestBody List<ItemOfOrderDTO> itemOfOrderDTOS) {
        itemOfOrderService.delete(itemOfOrderDTOS);
    }
}
