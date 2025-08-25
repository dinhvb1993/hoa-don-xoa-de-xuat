package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ProductAttributeGroupDTO;
import com.simple_official_spring_template.entity.ProductAttributeGroupEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductAttributeGroupId;
import com.simple_official_spring_template.repository.IAttributeGroupRepository;
import com.simple_official_spring_template.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductAttributeGroupConverter {
    @Autowired
    private IAttributeGroupRepository attributeGroupRepository;

    @Autowired
    private IProductRepository productRepository;

    public ProductAttributeGroupEntity toEntity(ProductAttributeGroupDTO productAttributeGroupDTO, ProductAttributeGroupEntity productAttributeGroupEntity) {
        if (productAttributeGroupEntity.getProductAttributeGroupId() == null){
            productAttributeGroupEntity.setProductAttributeGroupId(new ProductAttributeGroupId(
                    productAttributeGroupDTO.getAttributeGroupId(),
                    productAttributeGroupDTO.getProductId()
            ));

            productAttributeGroupEntity.setAttributeGroup(attributeGroupRepository.findOneById(productAttributeGroupDTO.getAttributeGroupId()));
            productAttributeGroupEntity.setProduct(productRepository.findOneById(productAttributeGroupDTO.getProductId()));
        }
        if (productAttributeGroupDTO.getAddtionPrice() != null){
            productAttributeGroupEntity.setAdditionPrice(productAttributeGroupDTO.getAddtionPrice());
        }
        return productAttributeGroupEntity;
    }

    public ProductAttributeGroupDTO toDTO(ProductAttributeGroupEntity productAttributeGroupEntity) {
        return new ProductAttributeGroupDTO(
                productAttributeGroupEntity.getAttributeGroup().getId(),
                productAttributeGroupEntity.getProduct().getId(),
                productAttributeGroupEntity.getAdditionPrice()
        );
    }
}
