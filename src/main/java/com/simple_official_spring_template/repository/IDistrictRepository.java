package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.CityEntity;
import com.simple_official_spring_template.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDistrictRepository extends JpaRepository<DistrictEntity, Integer> {
    DistrictEntity findOneById(Integer id);
    List<DistrictEntity> findByCity(CityEntity cityEntity);

    @Query(nativeQuery =true, value = "DELETE FROM district as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
