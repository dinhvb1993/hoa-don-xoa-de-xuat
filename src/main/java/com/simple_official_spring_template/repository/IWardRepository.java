package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.DistrictEntity;
import com.simple_official_spring_template.entity.WardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IWardRepository extends JpaRepository<WardEntity, Integer> {
    WardEntity findOneById(Integer id);
    List<WardEntity> findByDistrict(DistrictEntity districtEntity);

    @Query(nativeQuery =true, value = "DELETE FROM ward as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
