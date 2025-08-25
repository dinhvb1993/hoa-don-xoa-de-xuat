package com.simple_official_spring_template.statistic.nativequery;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;

@Repository
public class UserStatisticNativeQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public int getTotalOrder(String customerId, Timestamp startTime, Timestamp endTime) {
        String queryStr =
                "select count(*)\n" +
                "from product_order\n" +
                "    where customer_id = :customerId\n" +
                "        and cast(:startTime as timestamp) <= payment_time\n" +
                "        and payment_time <= cast(:endTime as timestamp);";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("customerId", customerId);
        query.setParameter("startTime", startTime.toString());
        query.setParameter("endTime", endTime.toString());
        return ((BigInteger) query.getSingleResult()).intValue();
    }

    public float getTotalPrice(String customerId, Timestamp startTime, Timestamp endTime) {
        String queryStr =
                "select sum(master_final_price)\n" +
                "from item_of_order i\n" +
                "    inner join product_order p\n" +
                "    on i.order_id = p.id\n" +
                "    where p.customer_id = :customerId\n" +
                "        and cast(:startTime as timestamp) <= p.payment_time\n" +
                "        and p.payment_time <= cast(:endTime as timestamp);";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("customerId", customerId);
        query.setParameter("startTime", startTime.toString());
        query.setParameter("endTime", endTime.toString());
        return query.getSingleResult() == null ? 0f : ((Float) query.getSingleResult()).floatValue();
    }

    public int getTotalExchangeOrReturnOrder(String customerId, Timestamp startTime, Timestamp endTime) {
        String queryStr =
                "select count(*)\n" +
                "from (\n" +
                "    select distinct i.order_id, p.customer_id\n" +
                "    from item_of_order i\n" +
                "        inner join product_order p\n" +
                "            on i.order_id = p.id\n" +
                "        where p.customer_id = :customerId\n" +
                "            and (\n" +
                "                (i.exchange_status = true and cast(:startTime as timestamp) <= i.exchange_time and i.exchange_time <= cast(:endTime as timestamp))\n" +
                "                or (i.return_status = true and cast(:startTime as timestamp) <= i.return_time and i.return_time <= cast(:endTime as timestamp))\n" +
                "            )\n" +
                ") as temp;";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("customerId", customerId);
        query.setParameter("startTime", startTime.toString());
        query.setParameter("endTime", endTime.toString());
        return ((BigInteger) query.getSingleResult()).intValue();
    }

}
