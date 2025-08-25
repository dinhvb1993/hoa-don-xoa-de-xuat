package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ProductProductTypeDTO;
import com.simple_official_spring_template.entity.embeddedid.ProductProductTypeId;

import java.util.List;

public interface IProductProductTypeService {
    List<ProductProductTypeDTO> findAll();
    List<ProductProductTypeDTO> findByProductIdAndProductTypeId(Integer productId, Integer productTypeId);
    ProductProductTypeDTO findOneByProductProductTypeId(ProductProductTypeId productProductTypeId);
    ProductProductTypeDTO save(ProductProductTypeDTO productProductTypeDTO);
    ProductProductTypeDTO update(ProductProductTypeDTO productProductTypeDTO);
    void delete(ProductProductTypeDTO productProductTypeDTO);

}
