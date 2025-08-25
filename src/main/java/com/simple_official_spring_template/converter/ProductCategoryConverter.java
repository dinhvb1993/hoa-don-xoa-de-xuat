package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ProductCategoryDTO;
import com.simple_official_spring_template.entity.ProductCategoryEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductCategoryId;
import com.simple_official_spring_template.repository.ICategoryRepository;
import com.simple_official_spring_template.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryConverter {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IProductRepository productRepository;

    public ProductCategoryEntity toEntity(ProductCategoryDTO productCategoryDTO, ProductCategoryEntity productCategoryEntity) {
        if (productCategoryEntity.getProductCategoryId() == null){
            productCategoryEntity.setProductCategoryId(new ProductCategoryId(
                    productCategoryDTO.getCategoryId(),
                    productCategoryDTO.getProductId()
            ));

            productCategoryEntity.setCategory(categoryRepository.findOneById(productCategoryDTO.getCategoryId()));
            productCategoryEntity.setProduct(productRepository.findOneById(productCategoryDTO.getProductId()));
        }
        return productCategoryEntity;
    }

    public ProductCategoryDTO toDTO(ProductCategoryEntity productCategoryEntity) {
        return new ProductCategoryDTO(
                productCategoryEntity.getCategory().getId(),
                productCategoryEntity.getProduct().getId()
        );
    }
}
