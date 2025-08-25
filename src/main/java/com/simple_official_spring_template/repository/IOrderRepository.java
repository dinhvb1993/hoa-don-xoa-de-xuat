package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUserId(String customerId);
    List<OrderEntity> findByUserIdAndCurrentStatusId(String userId, String currentStatusId);
    OrderEntity findOneById(Integer id);
    List<OrderEntity> findByCurrentStatusId(String currentOrderStatusId);

    @Query(nativeQuery =true, value = "DELETE FROM product_order as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
