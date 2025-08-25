package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface IPromotionRepository extends JpaRepository<PromotionEntity, Integer> {
    PromotionEntity findOneById(Integer id);
    PromotionEntity findOneByCode(String code);
    List<PromotionEntity> findByValidityPeriodAfter(Timestamp currentTime);

    @Query(nativeQuery =true, value = "DELETE FROM promotion as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
