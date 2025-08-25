package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ProductProductTypeDTO;
import com.simple_official_spring_template.entity.ProductProductTypeEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductProductTypeId;
import com.simple_official_spring_template.repository.IProductRepository;
import com.simple_official_spring_template.repository.IProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProductTypeConverter {
    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Autowired
    private IProductRepository productRepository;

    public ProductProductTypeEntity toEntity(ProductProductTypeDTO productProductTypeDTO, ProductProductTypeEntity productProductTypeEntity) {
        if (productProductTypeEntity.getProductProductTypeId() == null){
            productProductTypeEntity.setProductProductTypeId(new ProductProductTypeId(
                    productProductTypeDTO.getProductTypeId(),
                    productProductTypeDTO.getProductId()
            ));

            productProductTypeEntity.setProductType(productTypeRepository.findOneById(productProductTypeDTO.getProductTypeId()));
            productProductTypeEntity.setProduct(productRepository.findOneById(productProductTypeDTO.getProductId()));
        }
        return productProductTypeEntity;
    }

    public ProductProductTypeDTO toDTO(ProductProductTypeEntity productProductTypeEntity) {
        return new ProductProductTypeDTO(
                productProductTypeEntity.getProductType().getId(),
                productProductTypeEntity.getProduct().getId()
        );
    }
}
