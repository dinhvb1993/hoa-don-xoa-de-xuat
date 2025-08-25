package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IActionRepository extends JpaRepository<ActionEntity, Integer> {
    ActionEntity findOneById(Integer id);
    ActionEntity findOneByName(String name);


//    @Query(nativeQuery =true, value = "DELETE FROM action as t WHERE t.id IN :ids")
//    void deleteByIds(@Param("ids") Integer[] ids);

    void deleteByIdIn(Integer[] ids);
}
