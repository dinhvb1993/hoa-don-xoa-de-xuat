package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.ProductRateDTO;
import com.simple_official_spring_template.entity.ProductRateEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductRateId;
import com.simple_official_spring_template.repository.IProductRepository;
import com.simple_official_spring_template.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRateConverter {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProductRepository productRepository;

    public ProductRateEntity toEntity(ProductRateDTO productRateDTO, ProductRateEntity productRateEntity) {
        if (productRateEntity.getProductRateId() == null){
            productRateEntity.setProductRateId(new ProductRateId(
                    productRateDTO.getUserId(),
                    productRateDTO.getProductId()
            ));
            productRateEntity.setRate(productRateDTO.getRate());
            productRateEntity.setUser(userRepository.findOneById(productRateDTO.getUserId()));
            productRateEntity.setProduct(productRepository.findOneById(productRateDTO.getProductId()));
        }
        return productRateEntity;
    }

    public ProductRateDTO toDTO(ProductRateEntity productRateEntity) {
        return new ProductRateDTO(
                productRateEntity.getUser().getId(),
                productRateEntity.getProduct().getId(),
                productRateEntity.getRate()
        );
    }
}
