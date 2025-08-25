package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ProductAttributeGroupConverter;
import com.simple_official_spring_template.dto.ProductAttributeGroupDTO;
import com.simple_official_spring_template.entity.ProductAttributeGroupEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductAttributeGroupId;
import com.simple_official_spring_template.repository.IProductAttributeGroupRepository;
import com.simple_official_spring_template.repository.IProductRepository;
import com.simple_official_spring_template.service.IProductAttributeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAttributeGroupService implements IProductAttributeGroupService {
    @Autowired
    private IProductAttributeGroupRepository productAttributeGroupRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductAttributeGroupConverter productAttributeGroupConverter;

    @Override
    public List<ProductAttributeGroupDTO> findAll() {
        List<ProductAttributeGroupDTO> productAttributeGroupDTOS = new ArrayList<>();

        List<ProductAttributeGroupEntity> productAttributeGroupEntities = productAttributeGroupRepository.findAll();
        for (ProductAttributeGroupEntity productAttributeGroupEntity : productAttributeGroupEntities) {
            productAttributeGroupDTOS.add(productAttributeGroupConverter.toDTO(productAttributeGroupEntity));
        }

        return productAttributeGroupDTOS;
    }

    @Override
    public List<ProductAttributeGroupDTO> findByProductIdAndAttributeGroupId(Integer productId, Integer attributeGroupId) {
        List<ProductAttributeGroupDTO> productAttributeGroupDTOS = new ArrayList<>();

        List<ProductAttributeGroupEntity> productAttributeGroupEntities = null;
        
        if (productId != null && attributeGroupId != null){
            productAttributeGroupEntities = productAttributeGroupRepository.findByProductIdAndAttributeGroupId(productId, attributeGroupId);
        }
        else if (productId != null){
            productAttributeGroupEntities = productAttributeGroupRepository.findByProductId(productId);
        }
        else if (attributeGroupId != null){
            productAttributeGroupEntities = productAttributeGroupRepository.findByAttributeGroupId(attributeGroupId);
        }
        
        
        for (ProductAttributeGroupEntity productAttributeGroupEntity : productAttributeGroupEntities) {
            productAttributeGroupDTOS.add(productAttributeGroupConverter.toDTO(productAttributeGroupEntity));
        }

        return productAttributeGroupDTOS;
    }

    @Override
    public ProductAttributeGroupDTO findOneByProductAttributeGroupId(ProductAttributeGroupId productAttributeGroupId) {
        ProductAttributeGroupEntity productAttributeGroupEntity = productAttributeGroupRepository.findOneByProductAttributeGroupId(productAttributeGroupId);
        return productAttributeGroupConverter.toDTO(productAttributeGroupEntity);
    }

    @Override
    public ProductAttributeGroupDTO save(ProductAttributeGroupDTO productAttributeGroupDTO) {
        ProductAttributeGroupEntity productAttributeGroupEntity = productAttributeGroupConverter.toEntity(productAttributeGroupDTO, new ProductAttributeGroupEntity());
        productAttributeGroupEntity = productAttributeGroupRepository.save(productAttributeGroupEntity);
        return productAttributeGroupConverter.toDTO(productAttributeGroupEntity);
    }

    @Override
    public ProductAttributeGroupDTO update(ProductAttributeGroupDTO productAttributeGroupDTO) {
        return null;
    }

    @Override
    public void delete(ProductAttributeGroupDTO productAttributeGroupDTO) {
        for (ProductAttributeGroupId id: productAttributeGroupDTO.getProductAttributeGroupIds()) {
            productAttributeGroupRepository.deleteByProductAttributeGroupId(id);
        }
    }
}
