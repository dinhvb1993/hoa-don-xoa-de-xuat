package com.simple_official_spring_template.statistic.nativequery;

import com.simple_official_spring_template.statistic.result.TopBestSellerProductResult;
import com.simple_official_spring_template.statistic.result.TopInventoryProductResult;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SystemStatisticNativeQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public long getTotalOrder(Timestamp startTime, Timestamp endTime) {
        String queryStr =
                "select count(*)\n" +
                "from product_order\n" +
                "    where cast(:startTime as timestamp) <= payment_time\n" +
                "        and payment_time <= cast(:endTime as timestamp);";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("startTime", startTime.toString());
        query.setParameter("endTime", endTime.toString());
        return ((BigInteger) query.getSingleResult()).longValue();
    }

    public long getTotalExchangeOrReturnOfOrder(Timestamp startTime, Timestamp endTime) {
        String queryStr =
                "select count(*)\n" +
                "from (\n" +
                "    select distinct order_id\n" +
                "    from item_of_order\n" +
                "        where (exchange_status = true and cast(:startTime as timestamp) <= exchange_time and exchange_time <= cast(:endTime as timestamp))\n" +
                "            or (return_status = true and cast(:startTime as timestamp) <= return_time and return_time <= cast(:endTime as timestamp))\n" +
                ") as temp;";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("startTime", startTime.toString());
        query.setParameter("endTime", endTime.toString());
        return ((BigInteger) query.getSingleResult()).longValue();
    }

    public List<TopBestSellerProductResult> getTopBestSellerProduct(Timestamp startTime, Timestamp endTime, String orderBy, Integer limit) {
        String queryStr =
                "select\n" +
                "       pos.product_id,\n" +
                "       sum(ioo.quantity - (case when ioo.return_status = true then ioo.return_number else 0 end)) as final_quantity,\n" +
                "       sum(ioo.quantity) as quantity,\n" +
                "       sum(case when ioo.return_status = true then ioo.return_number else 0 end) as return_number,\n" +
                "       sum(ioo.master_final_price) as final_price\n" +
                "from item_of_order ioo\n" +
                "    inner join product_order po\n" +
                "        on ioo.order_id = po.id\n" +
                "    inner join product_of_shop pos\n" +
                "        on ioo.product_of_shop_id = pos.id\n" +
                "    where cast(:startTime as timestamp) <= po.payment_time and po.payment_time <= cast(:endTime as timestamp)\n" +
                "    group by pos.product_id";
        if (orderBy.equals("final_quantity")) {
            queryStr += "   order by final_quantity desc\n";
        }
        else if (orderBy.equals("final_price")) {
            queryStr += "   order by final_price desc\n";
        }
        queryStr += "   limit :limitNumber ;";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("startTime", startTime.toString());
        query.setParameter("endTime", endTime.toString());
        query.setParameter("limitNumber", limit);
        List<Object[]> resultList = query.getResultList();
        List<TopBestSellerProductResult> results = new ArrayList<>();
        for(Object[] o: resultList) {
            results.add(new TopBestSellerProductResult((String) o[0], ((BigInteger) o[1]).intValue(), ((BigInteger) o[2]).intValue(), ((BigInteger) o[3]).intValue(), ((Float) o[4]).floatValue()));
        }
        return results;
    }

    public List<TopInventoryProductResult> getTopInventoryProduct(Integer limit) {
        String queryStr =
                "select product_id, sum(quantity) as quantity\n" +
                "from product_of_shop\n" +
                "    group by product_id\n" +
                "    order by quantity desc\n" +
                "    limit :limitNumber ;";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("limitNumber", limit);
        List<Object[]> resultList = query.getResultList();
        List<TopInventoryProductResult> results = new ArrayList<>();
        for(Object[] o: resultList) {
            results.add(new TopInventoryProductResult((String) o[0], ((BigInteger) o[1]).intValue()));
        }
        return results;
    }


    public float getTotalPrice(Timestamp startTime, Timestamp endTime) {
        String queryStr =
                "select sum(ioo.master_final_price)\n" +
                "from item_of_order ioo\n" +
                "    left join product_order po\n" +
                "        on ioo.order_id = po.id\n" +
                "    where cast(:startTime as timestamp) <= po.payment_time and po.payment_time <= cast(:endTime as timestamp);";
        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        return query.getSingleResult() == null ? 0f : ((Float) query.getSingleResult()).floatValue();
    }
}
