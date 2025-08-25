package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ProductCategoryDTO;
import com.simple_official_spring_template.entity.embeddedid.ProductCategoryId;

import java.util.List;

public interface IProductCategoryService {
    List<ProductCategoryDTO> findAll();
    List<ProductCategoryDTO> findByProductIdAndCategoryId(Integer productId, Integer categoryId);
    ProductCategoryDTO findOneByProductCategoryId(ProductCategoryId productCategoryId);
    ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO);
    ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO);
    void delete(ProductCategoryDTO productCategoryDTO);

}
