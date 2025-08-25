package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ProductAttributeGroupDTO;
import com.simple_official_spring_template.entity.embeddedid.ProductAttributeGroupId;

import java.util.List;

public interface IProductAttributeGroupService {
    List<ProductAttributeGroupDTO> findAll();
    List<ProductAttributeGroupDTO> findByProductIdAndAttributeGroupId(Integer productId, Integer attributeGroupId);
    ProductAttributeGroupDTO findOneByProductAttributeGroupId(ProductAttributeGroupId productAttributeGroupId);
    ProductAttributeGroupDTO save(ProductAttributeGroupDTO productAttributeGroupDTO);
    ProductAttributeGroupDTO update(ProductAttributeGroupDTO productAttributeGroupDTO);
    void delete(ProductAttributeGroupDTO productAttributeGroupDTO);

}
