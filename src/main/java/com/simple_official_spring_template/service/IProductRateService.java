package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ProductRateDTO;
import com.simple_official_spring_template.entity.embeddedid.ProductRateId;

import java.util.List;

public interface IProductRateService {
    List<ProductRateDTO> findAll();
    List<ProductRateDTO> findByUserIdAndProductId(String customerId, String productId);
    ProductRateDTO findOneByProductRateId(ProductRateId productRateId);
    ProductRateDTO save(ProductRateDTO productRateDTO);
    ProductRateDTO update(ProductRateDTO productRateDTO);
    void delete(ProductRateDTO productRateDTO);

}
