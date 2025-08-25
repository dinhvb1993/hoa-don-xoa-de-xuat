package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer> {
    List<OrderStatusEntity> findByOrderIdOrderByTimeAsc(Integer orderId);
    OrderStatusEntity findOneById(Integer id);

    @Query(nativeQuery =true, value = "DELETE FROM order_status as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
