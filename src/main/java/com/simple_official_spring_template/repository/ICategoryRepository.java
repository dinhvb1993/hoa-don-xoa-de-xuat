package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findOneById(Integer id);
    CategoryEntity findOneBySlug(String slug);
    List<CategoryEntity> findByParent(CategoryEntity parentCategory);

    @Query(nativeQuery = true, value = "SELECT * FROM category as c WHERE c.id IN :categoryIds")
    Page<CategoryEntity> findByCategoryIds(@Param("categoryIds") Integer[] categoryIds, Pageable pageable);

    @Query(nativeQuery =true, value = "DELETE FROM category as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
