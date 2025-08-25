package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICityRepository extends JpaRepository<CityEntity, Integer> {
    CityEntity findOneById(Integer id);

    @Query(nativeQuery =true, value = "DELETE FROM city as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
