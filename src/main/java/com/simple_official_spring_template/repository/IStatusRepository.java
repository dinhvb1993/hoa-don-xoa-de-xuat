package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IStatusRepository extends JpaRepository<StatusEntity, Integer> {
    StatusEntity findOneById(String id);
    StatusEntity findOneByName(String name);

    @Query(nativeQuery =true, value = "DELETE FROM status as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
