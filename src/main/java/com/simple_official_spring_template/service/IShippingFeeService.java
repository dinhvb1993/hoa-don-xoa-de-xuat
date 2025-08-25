package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ShippingFeeDTO;

import java.util.List;

public interface IShippingFeeService {
    ShippingFeeDTO save(ShippingFeeDTO shippingFeeDTO);
    ShippingFeeDTO update(ShippingFeeDTO shippingFeeDTO);
    ShippingFeeDTO findOneById(Integer id);
    List<ShippingFeeDTO> findAll();
    ShippingFeeDTO countShippingFee(Float currentWeight, Boolean internalCity);
    void delete(ShippingFeeDTO shippingFeeDTO);
}
