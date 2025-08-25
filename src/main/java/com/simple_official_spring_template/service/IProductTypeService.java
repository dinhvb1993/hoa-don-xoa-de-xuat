package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.ProductTypeDTO;
import com.simple_official_spring_template.dto.others.ProductTypeGroupDTO;

import java.util.List;

public interface IProductTypeService {
    List<ProductTypeDTO> findAll();
    ProductTypeDTO findOneById(Integer id);
    ProductTypeDTO save(ProductTypeDTO productTypeDTO);
    ProductTypeDTO update(ProductTypeDTO productTypeDTO);
    void delete(ProductTypeDTO productTypeDTO);
    ProductTypeGroupDTO findInheritProductType(Integer parentProductTypeId, String orderBy, String orderType);
}
