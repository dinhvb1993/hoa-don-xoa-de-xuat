package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.ItemOfOrderEntity;
import com.simple_official_spring_template.entity.embeddedid.ItemOfOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemOfOrderRepository extends JpaRepository<ItemOfOrderEntity, ItemOfOrderId> {
    List<ItemOfOrderEntity> findByOrderId(Integer orderId);
    ItemOfOrderEntity findOneByItemOfOrderId(ItemOfOrderId itemOfOrderId);
    void deleteByItemOfOrderId(ItemOfOrderId itemOfOrderId);

    @Query(value = "SELECT t0.* FROM public.item_of_order t0\n" +
            "left join public.product_of_shop t1\n" +
            "on t0.product_of_shop_id = t1.id\n" +
            "left join public.shop t2\n" +
            "on t1.shop_id = t2.id\n" +
            "where t2.id = :shopId and t0.order_id = :orderId"
            , nativeQuery = true)
    List<ItemOfOrderEntity> findByShopIdAndOrderId(@Param("shopId") String shopId, @Param("orderId") Integer orderId);

    @Query(value = "select t0.* from item_of_order t0\n" +
            "left join public.product_order t1\n" +
            "on t0.order_id = t1.id\n" +
            "where t1.customer_id = :customerId\n" +
            "and t0.product_of_shop_id = :productId"
            , nativeQuery = true)
    List<ItemOfOrderEntity> findByUserIdAndProductId(@Param("customerId") String customerId, @Param("productId") Integer productId);

    @Query(nativeQuery =true, value = "DELETE FROM item_of_order as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
