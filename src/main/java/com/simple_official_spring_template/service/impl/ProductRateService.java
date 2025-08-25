package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ProductRateConverter;
import com.simple_official_spring_template.dto.ProductRateDTO;
import com.simple_official_spring_template.entity.ProductEntity;
import com.simple_official_spring_template.entity.ProductRateEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductRateId;
import com.simple_official_spring_template.repository.IProductRateRepository;
import com.simple_official_spring_template.repository.IProductRepository;
import com.simple_official_spring_template.service.IProductRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRateService implements IProductRateService {
    @Autowired
    private IProductRateRepository productRateRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductRateConverter productRateConverter;

    @Override
    public List<ProductRateDTO> findAll() {
        List<ProductRateDTO> productRateDTOS = new ArrayList<>();

        List<ProductRateEntity> productRateEntities = productRateRepository.findAll();
        for (ProductRateEntity productRateEntity : productRateEntities) {
            productRateDTOS.add(productRateConverter.toDTO(productRateEntity));
        }

        return productRateDTOS;
    }

    @Override
    public List<ProductRateDTO> findByUserIdAndProductId(String userId, String productId) {
        List<ProductRateDTO> productRateDTOS = new ArrayList<>();

        List<ProductRateEntity> productRateEntities = productRateRepository.findByUserIdAndProductId(userId, productId);
        for (ProductRateEntity productRateEntity : productRateEntities) {
            productRateDTOS.add(productRateConverter.toDTO(productRateEntity));
        }

        return productRateDTOS;
    }

    @Override
    public ProductRateDTO findOneByProductRateId(ProductRateId productRateId) {
        ProductRateEntity productRateEntity = productRateRepository.findOneByProductRateId(productRateId);
        return productRateConverter.toDTO(productRateEntity);
    }

    @Override
    public ProductRateDTO save(ProductRateDTO productRateDTO) {
        ProductRateEntity productRateEntity = productRateConverter.toEntity(productRateDTO, new ProductRateEntity());
        productRateEntity = productRateRepository.save(productRateEntity);

        Integer productRateNumber = null;
        Double averageProductRate = null;
        ProductEntity productEntity = null;

        if (productRateEntity.getProduct() != null){
            productEntity = productRateEntity.getProduct();
            productRateNumber = productEntity.getRateNumber();
            averageProductRate = productEntity.getAverageRate();
            if (productRateNumber == null) {
                productEntity.setRateNumber(1);
                productEntity.setAverageRate(productRateEntity.getRate());
            }
            else {
                productEntity.setRateNumber(productRateNumber + 1);
                productEntity.setAverageRate((averageProductRate * productRateNumber + productRateEntity.getRate()) / (productRateNumber + 1));
            }
            productRepository.save(productEntity);
        }


        return productRateConverter.toDTO(productRateEntity);
    }

    @Override
    public ProductRateDTO update(ProductRateDTO productRateDTO) {
        return null;
    }

    @Override
    public void delete(ProductRateDTO productRateDTO) {
        for (ProductRateId id: productRateDTO.getRateIds()) {
            productRateRepository.deleteById(id);
        }
    }
}
