package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPaymentRepository extends JpaRepository<PaymentEntity, Integer> {
    PaymentEntity findOneByOrderId(Integer orderId);
    PaymentEntity findOneByToken(String token);
    void deleteByToken(String token);

    @Query(nativeQuery =true, value = "DELETE FROM payment as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
