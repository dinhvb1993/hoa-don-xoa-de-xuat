package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ProductProductTypeConverter;
import com.simple_official_spring_template.dto.ProductProductTypeDTO;
import com.simple_official_spring_template.entity.ProductProductTypeEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductProductTypeId;
import com.simple_official_spring_template.repository.IProductProductTypeRepository;
import com.simple_official_spring_template.repository.IProductRepository;
import com.simple_official_spring_template.service.IProductProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductProductTypeService implements IProductProductTypeService {
    @Autowired
    private IProductProductTypeRepository productProductTypeRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductProductTypeConverter productProductTypeConverter;

    @Override
    public List<ProductProductTypeDTO> findAll() {
        List<ProductProductTypeDTO> productProductTypeDTOS = new ArrayList<>();

        List<ProductProductTypeEntity> productProductTypeEntities = productProductTypeRepository.findAll();
        for (ProductProductTypeEntity productProductTypeEntity : productProductTypeEntities) {
            productProductTypeDTOS.add(productProductTypeConverter.toDTO(productProductTypeEntity));
        }

        return productProductTypeDTOS;
    }

    @Override
    public List<ProductProductTypeDTO> findByProductIdAndProductTypeId(Integer productId, Integer productTypeId) {
        List<ProductProductTypeDTO> productProductTypeDTOS = new ArrayList<>();

        List<ProductProductTypeEntity> productProductTypeEntities = productProductTypeRepository.findByProductIdAndProductTypeId(productId, productTypeId);
        for (ProductProductTypeEntity productProductTypeEntity : productProductTypeEntities) {
            productProductTypeDTOS.add(productProductTypeConverter.toDTO(productProductTypeEntity));
        }

        return productProductTypeDTOS;
    }

    @Override
    public ProductProductTypeDTO findOneByProductProductTypeId(ProductProductTypeId productProductTypeId) {
        ProductProductTypeEntity productProductTypeEntity = productProductTypeRepository.findOneByProductProductTypeId(productProductTypeId);
        return productProductTypeConverter.toDTO(productProductTypeEntity);
    }

    @Override
    public ProductProductTypeDTO save(ProductProductTypeDTO productProductTypeDTO) {
        ProductProductTypeEntity productProductTypeEntity = productProductTypeConverter.toEntity(productProductTypeDTO, new ProductProductTypeEntity());
        productProductTypeEntity = productProductTypeRepository.save(productProductTypeEntity);
        return productProductTypeConverter.toDTO(productProductTypeEntity);
    }

    @Override
    public ProductProductTypeDTO update(ProductProductTypeDTO productProductTypeDTO) {
        return null;
    }

    @Override
    public void delete(ProductProductTypeDTO productProductTypeDTO) {
        for (ProductProductTypeId id: productProductTypeDTO.getProductProductTypeIds()) {
            productProductTypeRepository.deleteById(id);
        }
    }
}
