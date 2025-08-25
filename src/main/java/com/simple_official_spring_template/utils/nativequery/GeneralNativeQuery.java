package com.simple_official_spring_template.utils.nativequery;

import com.simple_official_spring_template.entity.UserEntity;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Service
public class GeneralNativeQuery {


    // addition
    public List<UserEntity> checkUserActionLicense(EntityManager entityManager, String userId, String actionName) {

        String queryStr;

        queryStr = "select u.*\n" +
                "\n" +
                "from user_account u\n" +
                "\n" +
                "join user_permission up on u.id = up.user_id\n" +
                "join permission_action pa on up.permission_id = pa.permission_id\n" +
                "join action a on a.id = pa.action_id\n" +
                "where up.licensed = true and pa.licensed = true and a.name = :actionName and u.id = :userId and u.is_active = true;";



        try {
            Query query = entityManager.createNativeQuery(queryStr);

            query.setParameter("userId", userId);
            query.setParameter("actionName", actionName);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    // end clone new category




    // addition
    public static List<Integer> filterProductIdsByAttributeGroupIds(EntityManager entityManager, List<Integer> ids, List<Integer> categoryIds, Integer limit, Integer offset) {

        String queryStr;
        if (categoryIds == null){
            queryStr =
                    "SELECT t0.product_id from product_attribute_group t0 " +
                            "where t0.attribute_group_id in (:ids) " +
                            "group by t0.product_id " +
                            "having count(t0.product_id) = " + ids.size() + " " +
                            "limit :limit offset :offset ;";
        }
        else {
            queryStr = "SELECT distinct product_id from product_category pc\n" +
                    "where pc.product_id IN\n" +
                    "      (SELECT t0.product_id from product_attribute_group t0\n" +
                    "where t0.attribute_group_id in (:ids)\n" +
                    "group by t0.product_id\n" +
                    "having count(t0.product_id) = " + ids.size() +")\n" +
                    "and category_id in (:categoryIds)\n" +
                    "limit :limit offset :offset ;";
        }


        try {
            Query query = entityManager.createNativeQuery(queryStr);

            query.setParameter("ids", ids);
            if (categoryIds != null){
                query.setParameter("categoryIds", categoryIds);
            }
            query.setParameter("limit", limit);
            query.setParameter("offset", offset);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    @Transactional
    public void deleteImageSliderItem(EntityManager entityManager, String tableName, String columnCatIdName, Integer cateId, Integer imageSliderItemId) {

        String queryStr = "DELETE FROM "+tableName+"\n" +
                "WHERE "+columnCatIdName+" = "+cateId+" AND image_slider_item = "+imageSliderItemId+";";

        try {
            Query query = entityManager.createNativeQuery(queryStr);
            query.executeUpdate();


//            query.setParameter("columnCatIdName", columnCatIdName);
//            query.setParameter("cateId", cateId);
//            query.setParameter("imageSliderItemId", cateId);

//            query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    public List<Object[]> findGeneralProducts(EntityManager entityManager, Integer categoryId, String name){
        name = StringUtil.unUnicodeAndRemoveSpace(name," ", null);
        String queryStr = "select p.id, p.name from product p\n";

        if (categoryId != null){
            queryStr += "inner join product_category pc on p.id = pc.product_id\n";
        }

        queryStr += "where p.naked_name like '%" + name + "%' \n";

        if (categoryId != null){
            queryStr += "AND pc.category_id="+categoryId+" \n";
        }

        queryStr += "LIMIT 8";


        try {
            Query query = entityManager.createNativeQuery(queryStr);


            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }




    public List<Object[]> groupInvoice(EntityManager entityManager, String year, String month, String date){

        String queryStr = "select customer_id, customer_name, original_link, sum(money) as sum, count(*) as count\n" +
                "from invoice\n";


        String condition = "where";

        if (year != null){
            condition += " year=" + "'" + year + "'";
            if (month != null){
                condition += " AND month=" + "'" + month + "'";
                if (date != null){
                    condition += " AND date=" + "'" + date + "'";
                }
            }
        }


        if (!condition.equals("where")){
            queryStr += condition + "\n";
        }




        queryStr += "group by customer_id,customer_name,original_link\n" +
                "order by sum DESC";




        try {
            Query query = entityManager.createNativeQuery(queryStr);


            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


}
