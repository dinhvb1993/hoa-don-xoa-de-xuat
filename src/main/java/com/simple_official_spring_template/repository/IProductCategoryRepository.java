package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ProductCategoryEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductCategoryId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductCategoryRepository extends JpaRepository<ProductCategoryEntity, ProductCategoryId> {
    List<ProductCategoryEntity> findByProductIdAndCategoryId(Integer productId, Integer categoryId);
    List<ProductCategoryEntity> findByProductId(Integer productId);
    Page<ProductCategoryEntity> findByProductId(Integer productId, Pageable pageable);
    List<ProductCategoryEntity> findByCategoryId(Integer categoryId);
    Page<ProductCategoryEntity> findByCategoryId(Integer categoryId, Pageable pageable);
    ProductCategoryEntity findOneByProductCategoryId(ProductCategoryId productCategoryId);

//    List<Integer> filterProductIdsByAttributeGroupIds(EntityManager entityManager, List<Integer> ids, Integer limit, Integer offset) {
//        String queryStr =
//                "SELECT t0.product_id from product_attribute_group t0 " +
//                        "where t0.attribute_group_id in (:ids) " +
//                        "group by t0.product_id " +
//                        "having count(t0.product_id) = " + ids.size() + " " +
//                        "limit :limit offset :offset ;";
//        try {
//            Query query = entityManager.createNativeQuery(queryStr);
//
//            query.setParameter("ids", ids);
//            query.setParameter("limit", limit);
//            query.setParameter("offset", offset);
//
//            return query.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }


    @Query(nativeQuery =true, value = "select DISTINCT t0.product_id from public.product_category t0 where t0.category_id IN (:categoryIds) ")
    Page<Integer> findByCategoryIds(@Param("categoryIds") List<Integer> categoryIds, Pageable pageable);

    @Query(nativeQuery =true, value = "select DISTINCT t0.product_id from public.product_category t0 where t0.category_id IN (:categoryIds) ")
    List<Integer> findByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

    @Query(nativeQuery =true, value = "select COUNT(*) FROM (select DISTINCT t0.product_id from public.product_category t0 where t0.category_id IN (:categoryIds))  as t1")
    Integer countByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);
}
