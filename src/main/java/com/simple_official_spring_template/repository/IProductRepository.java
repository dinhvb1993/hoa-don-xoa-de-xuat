package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findOneById(Integer id);
    ProductEntity findOneBySlug(String slug);


//    List<ProductEntity> findByPromotionId(Integer promotionId);
//    Page<ProductEntity> findByCategoryId(Integer categoryId, Pageable pageable);

    @Query(nativeQuery =true, value = "SELECT * FROM product as p WHERE p.id IN :ids")   // 3. Spring JPA In cause using native query
    Page<ProductEntity> findByIds(@Param("ids") List<Integer> ids, Pageable pageable);


    void deleteById(Integer id);

    @Query(nativeQuery =true, value = "DELETE FROM product as p WHERE p.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
