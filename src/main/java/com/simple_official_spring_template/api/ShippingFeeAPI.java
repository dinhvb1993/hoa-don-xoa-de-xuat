package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.ShippingFeeDTO;
import com.simple_official_spring_template.service.IShippingFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShippingFeeAPI {
    @Autowired
    private IShippingFeeService shippingFeeService;

    @GetMapping(value = "/api-shipping-fee/{id}")
    public ShippingFeeDTO getShippingFeeById(@PathVariable(value = "id", required = true) Integer id){
        return shippingFeeService.findOneById(id);
    }

    @GetMapping(value = "/api-shipping-fee")
    public List<ShippingFeeDTO> getShippingFees(){
        return shippingFeeService.findAll();
    }

    @GetMapping(value = "/api-shipping-fee-total")
    public ShippingFeeDTO getTotalShippingFees(@RequestParam Float currentWeight, @RequestParam Boolean internalCity){
        return shippingFeeService.countShippingFee(currentWeight, internalCity);
    }


    @PostMapping(value = "/api-shipping-fee")
    public ShippingFeeDTO createShippingFee(@RequestBody ShippingFeeDTO shippingFeeDTO){
        return shippingFeeService.save(shippingFeeDTO);
    }

    @PutMapping(value = "/api-shipping-fee")
    public ShippingFeeDTO updateShippingFee(@RequestBody ShippingFeeDTO shippingFeeDTO){
        return shippingFeeService.update(shippingFeeDTO);
    }

    @DeleteMapping(value = "/api-shipping-fee")
    public void deleteShippingFees(@RequestBody ShippingFeeDTO shippingFeeDTO){
        shippingFeeService.delete(shippingFeeDTO);
    }
}
