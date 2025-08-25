package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.InvoiceEntity;
import com.simple_official_spring_template.entity.InvoiceOriLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface IInvoiceOriLinkRepository extends JpaRepository<InvoiceOriLinkEntity, Integer> {
    InvoiceOriLinkEntity findOneById(Integer id);
    InvoiceOriLinkEntity findOneByLink(String link);

    @Query(nativeQuery =true, value = "DELETE FROM invoice_ori_link as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);


    @Query(nativeQuery =true, value = "SELECT * FROM invoice_ori_link as t WHERE t.id IN :ids")
    List<InvoiceOriLinkEntity> findByCustomerIdInList(@Param("ids") String[] ids);


}
