package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.AttributeGroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAttributeGroupRepository extends JpaRepository<AttributeGroupEntity, Integer> {
    AttributeGroupEntity findOneById(Integer id);
    AttributeGroupEntity findOneBySlug(String slug);
    List<AttributeGroupEntity> findByParent(AttributeGroupEntity parentAttributeGroup);

    @Query(nativeQuery = true, value = "SELECT * FROM attributeGroup as c WHERE c.id IN :attributeGroupIds")
    Page<AttributeGroupEntity> findByAttributeGroupIds(@Param("attributeGroupIds") List<Integer> attributeGroupIds, Pageable pageable);

    @Query(nativeQuery =true, value = "DELETE FROM attribute_group as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
