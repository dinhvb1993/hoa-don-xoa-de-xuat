package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ShippingFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IShippingFeeRepository extends JpaRepository<ShippingFeeEntity, Integer> {
    ShippingFeeEntity findOneById(Integer id);
    ShippingFeeEntity findOneByMinWeightLessThanEqualAndMaxWeightGreaterThanAndInternalCity(Float currentWeight, Float cloneCurrentWeight, Boolean internalCity);
    ShippingFeeEntity findOneByMinWeightLessThanAndInternalCity(Float minWeight, Boolean internalCity);

    @Query(nativeQuery =true, value = "DELETE FROM shipping_fee as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
