package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ProductCategoryConverter;
import com.simple_official_spring_template.dto.ProductCategoryDTO;
import com.simple_official_spring_template.entity.ProductCategoryEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductCategoryId;
import com.simple_official_spring_template.repository.IProductCategoryRepository;
import com.simple_official_spring_template.repository.IProductRepository;
import com.simple_official_spring_template.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService implements IProductCategoryService {
    @Autowired
    private IProductCategoryRepository productCategoryRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductCategoryConverter productCategoryConverter;

    @Override
    public List<ProductCategoryDTO> findAll() {
        List<ProductCategoryDTO> productCategoryDTOS = new ArrayList<>();

        List<ProductCategoryEntity> productCategoryEntities = productCategoryRepository.findAll();
        for (ProductCategoryEntity productCategoryEntity : productCategoryEntities) {
            productCategoryDTOS.add(productCategoryConverter.toDTO(productCategoryEntity));
        }

        return productCategoryDTOS;
    }

    @Override
    public List<ProductCategoryDTO> findByProductIdAndCategoryId(Integer productId, Integer categoryId) {
        List<ProductCategoryDTO> productCategoryDTOS = new ArrayList<>();

        List<ProductCategoryEntity> productCategoryEntities = null;
        if (productId != null && categoryId != null){
            productCategoryEntities = productCategoryRepository.findByProductIdAndCategoryId(productId, categoryId);
        }
        else if (productId != null){
            productCategoryEntities = productCategoryRepository.findByProductId(productId);
        }
        else if (categoryId != null){
            productCategoryEntities = productCategoryRepository.findByCategoryId(categoryId);
        }


        assert productCategoryEntities != null;
        for (ProductCategoryEntity productCategoryEntity : productCategoryEntities) {
            productCategoryDTOS.add(productCategoryConverter.toDTO(productCategoryEntity));
        }

        return productCategoryDTOS;
    }

    @Override
    public ProductCategoryDTO findOneByProductCategoryId(ProductCategoryId productCategoryId) {
        ProductCategoryEntity productCategoryEntity = productCategoryRepository.findOneByProductCategoryId(productCategoryId);
        return productCategoryConverter.toDTO(productCategoryEntity);
    }

    @Override
    public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity productCategoryEntity = productCategoryConverter.toEntity(productCategoryDTO, new ProductCategoryEntity());
        productCategoryEntity = productCategoryRepository.save(productCategoryEntity);
        return productCategoryConverter.toDTO(productCategoryEntity);
    }

    @Override
    public ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO) {
        return null;
    }

    @Override
    public void delete(ProductCategoryDTO productCategoryDTO) {
        for (ProductCategoryId id: productCategoryDTO.getProductCategoryIds()) {
            productCategoryRepository.deleteById(id);
        }
    }
}
