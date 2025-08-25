package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ProductAttributeGroupEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductAttributeGroupId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductAttributeGroupRepository extends JpaRepository<ProductAttributeGroupEntity, ProductAttributeGroupId> {
    List<ProductAttributeGroupEntity> findByProductIdAndAttributeGroupId(Integer productId, Integer attributeGroupId);
    List<ProductAttributeGroupEntity> findByProductId(Integer productId);
    Page<ProductAttributeGroupEntity> findByProductId(Integer productId, Pageable pageable);
    List<ProductAttributeGroupEntity> findByAttributeGroupId(Integer attributeGroupId);
    Page<ProductAttributeGroupEntity> findByAttributeGroupId(Integer attributeGroupId, Pageable pageable);
    ProductAttributeGroupEntity findOneByProductAttributeGroupId(ProductAttributeGroupId productAttributeGroupId);
    
    @Query(nativeQuery =true, value = "select DISTINCT t0.product_id from public.product_attribute_group t0 where t0.attribute_group_id IN (:attributeGroupIds) ")
    Page<Integer> findByAttributeGroupIds(@Param("attributeGroupIds") List<Integer> attributeGroupIds, Pageable pageable);

    @Query(nativeQuery =true, value = "select DISTINCT t0.product_id from public.product_attribute_group t0 where t0.attribute_group_id IN (:attributeGroupIds) ")
    List<Integer> findByAttributeGroupIds(@Param("attributeGroupIds") List<Integer> attributeGroupIds);

    void deleteAllByProductId(Integer productId);
    void deleteAllByAttributeGroupId(Integer attributeGroupId);

    void deleteByProductAttributeGroupId(ProductAttributeGroupId productAttributeGroupId);
}
