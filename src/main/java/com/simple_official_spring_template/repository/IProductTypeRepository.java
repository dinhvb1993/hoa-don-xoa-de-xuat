package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ProductTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductTypeRepository extends JpaRepository<ProductTypeEntity, Integer> {
    ProductTypeEntity findOneById(Integer id);
    ProductTypeEntity findOneBySlug(String slug);
    List<ProductTypeEntity> findByParent(ProductTypeEntity parentProductType);

    @Query(nativeQuery = true, value = "SELECT * FROM productType as c WHERE c.id IN :productTypeIds")
    Page<ProductTypeEntity> findByProductTypeIds(@Param("productTypeIds") List<Integer> productTypeIds, Pageable pageable);

    @Query(nativeQuery =true, value = "DELETE FROM product_type as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
