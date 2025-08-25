package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ProductRateEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductRateId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRateRepository extends JpaRepository<ProductRateEntity, ProductRateId> {
    List<ProductRateEntity> findByUserIdAndProductId(String customerId, String productId);
    ProductRateEntity findOneByProductRateId(ProductRateId productRateId);
}
