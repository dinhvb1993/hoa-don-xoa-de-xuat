package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ProductProductTypeEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductProductTypeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductProductTypeRepository extends JpaRepository<ProductProductTypeEntity, ProductProductTypeId> {
    List<ProductProductTypeEntity> findByProductIdAndProductTypeId(Integer productId, Integer productTypeId);
    List<ProductProductTypeEntity> findByProductId(Integer productId);
    Page<ProductProductTypeEntity> findByProductId(Integer productId, Pageable pageable);
    List<ProductProductTypeEntity> findByProductTypeId(Integer productTypeId);
    Page<ProductProductTypeEntity> findByProductTypeId(Integer productTypeId, Pageable pageable);
    ProductProductTypeEntity findOneByProductProductTypeId(ProductProductTypeId productProductTypeId);


    @Query(nativeQuery =true, value = "select DISTINCT t0.product_id from public.product_product_type t0 where t0.product_type_id IN (:productTypeIds) ")
    Page<Integer> findByProductTypeIds(@Param("productTypeIds") List<Integer> productTypeIds, Pageable pageable);

    @Query(nativeQuery =true, value = "select DISTINCT t0.product_id from public.product_product_type t0 where t0.product_type_id IN (:productTypeIds) ")
    List<Integer> findByProductTypeIds(@Param("productTypeIds") List<Integer> productTypeIds);
}
