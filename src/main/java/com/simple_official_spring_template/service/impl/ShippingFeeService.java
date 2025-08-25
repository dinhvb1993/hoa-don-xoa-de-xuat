package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ShippingFeeConverter;
import com.simple_official_spring_template.dto.ShippingFeeDTO;
import com.simple_official_spring_template.entity.ShippingFeeEntity;
import com.simple_official_spring_template.repository.IShippingFeeRepository;
import com.simple_official_spring_template.service.IShippingFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingFeeService implements IShippingFeeService {

    @Autowired
    private IShippingFeeRepository shippingFeeRepository;

    @Autowired
    private ShippingFeeConverter shippingFeeConverter;


    @Override
    public ShippingFeeDTO save(ShippingFeeDTO shippingFeeDTO) {
        ShippingFeeEntity shippingFeeEntity = shippingFeeConverter.toEntity(shippingFeeDTO, new ShippingFeeEntity());
        return shippingFeeConverter.toDTO(shippingFeeRepository.save(shippingFeeEntity));
    }

    @Override
    public ShippingFeeDTO update(ShippingFeeDTO shippingFeeDTO) {
        ShippingFeeEntity shippingFeeEntity = shippingFeeRepository.findOneById(shippingFeeDTO.getId());
        shippingFeeEntity = shippingFeeConverter.toEntity(shippingFeeDTO, shippingFeeEntity);
        return shippingFeeConverter.toDTO(shippingFeeRepository.save(shippingFeeEntity));
    }

    @Override
    public ShippingFeeDTO findOneById(Integer id) {
        return shippingFeeConverter.toDTO(shippingFeeRepository.findOneById(id));
    }

    @Override
    public List<ShippingFeeDTO> findAll() {
        List<ShippingFeeDTO> shippingFeeDTOS = new ArrayList<>();
        for (ShippingFeeEntity shippingFeeEntity: shippingFeeRepository.findAll()){
            shippingFeeDTOS.add(shippingFeeConverter.toDTO(shippingFeeEntity));
        }
        return shippingFeeDTOS;
    }

    @Override
    public ShippingFeeDTO countShippingFee(Float currentWeight, Boolean internalCity) {
        ShippingFeeEntity shippingFeeEntity = shippingFeeRepository.findOneByMinWeightLessThanEqualAndMaxWeightGreaterThanAndInternalCity(
                currentWeight,
                currentWeight,
                internalCity
        );
        ShippingFeeDTO resultShippingFee = shippingFeeConverter.toDTO(shippingFeeEntity);

        resultShippingFee.setCurrentWeight(currentWeight);

        Float totalPrice = resultShippingFee.getPrice();

        if (shippingFeeEntity.getAdditionalPrice() > 0){
            totalPrice += (float)Math.ceil(resultShippingFee.getCurrentWeight() - resultShippingFee.getMinWeight()) * resultShippingFee.getAdditionalPrice();
        }
        resultShippingFee.setTotalPrice(totalPrice);

        return resultShippingFee;
//        return null;
    }

    @Override
    public void delete(ShippingFeeDTO shippingFeeDTO) {
        shippingFeeRepository.deleteByIds(shippingFeeDTO.getIds());
    }


}
